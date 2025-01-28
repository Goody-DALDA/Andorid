package com.goody.dalda.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.repository.alcohol.AlcoholRepository
import com.goody.dalda.data.repository.search.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel
    @Inject
    constructor(
        private val alcoholRepository: AlcoholRepository,
        private val searchRepository: SearchRepository,
    ) : ViewModel() {
        private val _query = MutableStateFlow("")
        val query: StateFlow<String> = _query

        private val _recentSearchWordList = MutableStateFlow(emptyList<String>())
        val recentSearchWordList: StateFlow<List<String>> = _recentSearchWordList

        private val _recommendAlcoholList = MutableStateFlow(emptyList<String>())

        @OptIn(FlowPreview::class)
        val recommendAlcoholList =
            _recommendAlcoholList
                .debounce(500L)
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(),
                    initialValue = emptyList(),
                )

        private val _searchResultList = MutableStateFlow(emptyList<AlcoholData>())
        val searchResultList: StateFlow<List<AlcoholData>> = _searchResultList

        private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.RecentSearch)
        val uiState: StateFlow<SearchUiState> = _uiState

        fun setQuery(query: String) {
            _query.value = query
        }

        fun setUiState(uiState: SearchUiState) {
            _uiState.value = uiState
        }

        fun fetchRecentSearchWordList(isDesc: Boolean) {
            viewModelScope.launch(Dispatchers.IO) {
                _recentSearchWordList.value =
                    searchRepository
                        .getSearchWordList(
                            isDesc = isDesc,
                        )
            }
        }

        fun insertSearchWord(searchWord: String) {
            if (searchWord.isBlank()) return
            viewModelScope.launch(Dispatchers.IO) {
                searchRepository.insertSearchWord(searchWord)
            }
        }

        fun deleteAllSearchWord() {
            viewModelScope.launch(Dispatchers.IO) {
                searchRepository.deleteAllSearchWord()
            }
        }

        fun updateRecommendAlcoholList() {
            viewModelScope.launch(Dispatchers.IO) {
                _recommendAlcoholList.value = alcoholRepository.getRecommendAlcoholList(query.value)
            }
        }

        fun searchAlcoholData(query: String) {
            viewModelScope.launch(Dispatchers.IO) {
                val searchResult = alcoholRepository.getSearchedAlcoholData(query)
                val alcoholDataList = mutableListOf<AlcoholData>()

                alcoholDataList.addAll(searchResult.beerList)
                alcoholDataList.addAll(searchResult.sojuList)
                alcoholDataList.addAll(searchResult.sakeList)
                alcoholDataList.addAll(searchResult.wineList)
                alcoholDataList.addAll(searchResult.whiskyList)
                alcoholDataList.addAll(searchResult.traditionalLiquorList)

                _searchResultList.value = alcoholDataList
            }
        }
    }
