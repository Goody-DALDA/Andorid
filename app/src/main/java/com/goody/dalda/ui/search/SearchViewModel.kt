package com.goody.dalda.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.AlcoholInfo
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.data.repository.home.AlcoholRepository
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
class SearchViewModel @Inject constructor(
    private val alcoholRepository: AlcoholRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _recentSearchWordList = MutableStateFlow(emptyList<String>())
    val recentSearchWordList: StateFlow<List<String>> = _recentSearchWordList

    private val _recommendAlcoholList = MutableStateFlow(emptyList<String>())

    @OptIn(FlowPreview::class)
    val recommendAlcoholList = _recommendAlcoholList
        .debounce(500L).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    private val _searchResultList = MutableStateFlow(emptyList<AlcoholInfo>())
    val searchResultList: StateFlow<List<AlcoholInfo>> = _searchResultList


    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.RecentSearch)
    val uiState: StateFlow<SearchUiState> = _uiState

    fun setQuery(query: String) {
        _query.value = query
    }

    fun setUiState(uiState: SearchUiState) {
        _uiState.value = uiState
    }

    fun getRecentSearchWordList() {
//        _recentSearchWordList.value =
    }

    fun updateRecommendAlcoholList() {
        viewModelScope.launch(Dispatchers.IO) {
            _recommendAlcoholList.value = alcoholRepository.getRecommendAlcoholList(query.value)
        }
    }

    fun searchAlcoholInfo(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val searchResult = alcoholRepository.getSearchedAlcoholInfo(query)
            val alcoholInfoList = mutableListOf<AlcoholInfo>()

            alcoholInfoList.addAll(searchResult.sojuList.map { it.toAlcoholInfo(AlcoholType.SOJU) })
            alcoholInfoList.addAll(searchResult.beerList.map { it.toAlcoholInfo(AlcoholType.BEER) })
            alcoholInfoList.addAll(searchResult.sakeList.map { it.toAlcoholInfo(AlcoholType.SAKE) })
            alcoholInfoList.addAll(searchResult.wineList.map { it.toAlcoholInfo(AlcoholType.WINE) })
            alcoholInfoList.addAll(searchResult.wiskyList.map { it.toAlcoholInfo(AlcoholType.WISKY) })
            alcoholInfoList.addAll(searchResult.traditionalLiquorList.map {
                it.toAlcoholInfo(
                    AlcoholType.TRADITIONALLIQUOR
                )
            })

            _searchResultList.value = alcoholInfoList
        }
    }

    private fun <T> T.toAlcoholInfo(type: AlcoholType): AlcoholInfo {
        return when (this) {
            is AlcoholData.Soju -> AlcoholInfo(id, imgUrl, name, type, abv)
            is AlcoholData.Beer -> AlcoholInfo(id, imgUrl, name, type, abv)
            is AlcoholData.Sake -> AlcoholInfo(id, imgUrl, name, type, abv)
            is AlcoholData.Wine -> AlcoholInfo(id, imgUrl, name, type, abv)
            is AlcoholData.Wisky -> AlcoholInfo(id, imgUrl, name, type, abv)
            is AlcoholData.TraditionalLiquor -> AlcoholInfo(id, imgUrl, name, type, abv)
            else -> throw IllegalArgumentException("Unknown alcohol type")
        }
    }

}
