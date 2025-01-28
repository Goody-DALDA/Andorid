package com.goody.dalda.ui.label_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.data.repository.alcohol.AlcoholRepository
import com.goody.dalda.ui.dialog.SpiritsSearchResult
import com.goody.dalda.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LabelSearchViewModel
    @Inject
    constructor(
        private val repository: AlcoholRepository,
    ) : ViewModel() {
        private val _state = MutableLiveData<UiState<List<SpiritsSearchResult>>>(UiState.Uninitialized)
        val state: LiveData<UiState<List<SpiritsSearchResult>>> get() = _state

        fun requestSearchApi(searchText: String) {
            viewModelScope.launch(Dispatchers.IO) {
                val searchResult = repository.getSearchedAlcoholData(searchText)
                val alcoholDataList = mutableListOf<AlcoholData>()
                alcoholDataList.addAll(searchResult.beerList)
                alcoholDataList.addAll(searchResult.sojuList)
                alcoholDataList.addAll(searchResult.sakeList)
                alcoholDataList.addAll(searchResult.wineList)
                alcoholDataList.addAll(searchResult.whiskyList)
                alcoholDataList.addAll(searchResult.traditionalLiquorList)

                val spirit = alcoholDataList.firstOrNull()

                if (spirit != null) {
                    val results =
                        listOf(
                            SpiritsSearchResult(
                                spirit.name,
                                getCategory(spirit),
                                spirit.abv,
                                spirit.imgUrl,
                            ),
                        )
                    _state.postValue(UiState.Success(results))
                } else {
                    _state.postValue(UiState.Empty)
                }
            }
        }

        private fun getCategory(alcoholData: AlcoholData): String =
            when (alcoholData) {
                is AlcoholData.Soju -> AlcoholType.SOJU.alcoholName
                is AlcoholData.Beer -> AlcoholType.BEER.alcoholName
                is AlcoholData.Sake -> AlcoholType.SAKE.alcoholName
                is AlcoholData.Wine -> AlcoholType.WINE.alcoholName
                is AlcoholData.Whisky -> AlcoholType.WHISKY.alcoholName
                is AlcoholData.TraditionalLiquor -> AlcoholType.TRADITIONALLIQUOR.alcoholName
            }
    }
