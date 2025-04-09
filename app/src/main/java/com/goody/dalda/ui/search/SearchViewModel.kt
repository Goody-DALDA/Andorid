package com.goody.dalda.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.model.AlcoholUIModel
import com.goody.dalda.data.model.toUIModelList
import com.oyj.domain.usecase.search.DeleteAllSearchWordUseCase
import com.oyj.domain.usecase.search.DeleteSearchWordUseCase
import com.oyj.domain.usecase.search.GetRecommendAlcoholListUseCase
import com.oyj.domain.usecase.search.GetRecentSearchWordListUseCase
import com.oyj.domain.usecase.search.SearchAlcoholUseCase
import com.oyj.domain.usecase.search.InsertSearchWordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchAlcoholUseCase: SearchAlcoholUseCase,
    private val getRecentSearchWordListUseCase: GetRecentSearchWordListUseCase,
    private val getRecommendAlcoholListUseCase: GetRecommendAlcoholListUseCase,
    private val deleteSearchWordUseCase: DeleteSearchWordUseCase,
    private val deleteAllSearchWordUseCase: DeleteAllSearchWordUseCase,
    private val insertSearchWordUseCase: InsertSearchWordUseCase
) : ViewModel() {
    private val _sideEffect = MutableStateFlow<SearchSideEffect>(SearchSideEffect.Default)
    val sideEffect: StateFlow<SearchSideEffect> = _sideEffect

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _recentSearchWordList = MutableStateFlow(emptyList<String>())
    val recentSearchWordList: StateFlow<List<String>> = _recentSearchWordList

    private val _recommendAlcoholList = MutableStateFlow(emptyList<String>())

    @OptIn(FlowPreview::class)
    val recommendAlcoholList = _recommendAlcoholList
        .debounce(500L)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList(),
        )

    private val _searchResultList = MutableStateFlow(emptyList<AlcoholUIModel>())
    val searchResultList: StateFlow<List<AlcoholUIModel>> = _searchResultList

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.RecentSearch)
    val uiState: StateFlow<SearchUiState> = _uiState

    private fun setQuery(query: String) {
        _query.value = query
    }

    private fun setUiState(uiState: SearchUiState) {
        _uiState.value = uiState
    }

    private fun updateRecommendAlcoholList() {
        viewModelScope.launch(Dispatchers.IO) {
            getRecommendAlcoholListUseCase(query.value)
                .collect {
                    _recommendAlcoholList.value = it
                }
        }
    }

    private fun insertSearchWord(searchWord: String) {
        if (searchWord.isBlank()) return
        viewModelScope.launch(Dispatchers.IO) {
            insertSearchWordUseCase(searchWord)
        }
    }

    private fun deleteAllSearchWord() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllSearchWordUseCase()
        }
    }

    fun fetchRecentSearchWordList(isDesc: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            getRecentSearchWordListUseCase(isDesc = isDesc).collect {
                _recentSearchWordList.value = it
            }
        }
    }

    fun clearRecentSearchWord() {
        deleteAllSearchWord()
        fetchRecentSearchWordList(true)
    }

    private fun searchAlcoholData(query: String) = viewModelScope.launch(Dispatchers.IO) {
        searchAlcoholUseCase(query)
            .map { it.toUIModelList() }
            .collect {
                _searchResultList.value = it
            }
    }

    fun queryChanged(query: String) {
        setQuery(query)
        if (query.isNotEmpty()) {
            setUiState(SearchUiState.Recommendation)
            updateRecommendAlcoholList()
        } else {
            setUiState(SearchUiState.RecentSearch)
            fetchRecentSearchWordList(true)
        }
    }

    fun search(
        query: String,
        onSearchComplete: (suspend () -> Unit)? = null
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            insertSearchWord(query)
            val searchJob = searchAlcoholData(query)
            searchJob.join()
            setUiState(SearchUiState.SearchResult)
            onSearchComplete?.invoke()
        }
    }

    fun onClickRecommend(query: String) {
        setQuery(query)
        search(
            query = query,
            onSearchComplete = {
                _searchResultList.value.firstOrNull()?.let { firstResult ->
                    setSideEffect(SearchSideEffect.NavigateToLiquorDetail(firstResult))
                }
            }
        )
    }

    fun setSideEffect(sideEffect: SearchSideEffect) {
        _sideEffect.value = sideEffect
    }
}
