package com.goody.dalda.ui.label_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.data.mapper.DomainToPresenter.toAlcoholDataList
import com.goody.dalda.ui.dialog.SpiritsSearchResult
import com.goody.dalda.ui.state.UiState
import com.oyj.domain.usecase.search.SearchAlcoholUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LabelSearchViewModel @Inject constructor(
    private val searchAlcoholUseCase: SearchAlcoholUseCase,
) : ViewModel() {
    private val _state = MutableLiveData<UiState<List<SpiritsSearchResult>>>(UiState.Uninitialized)
    val state: LiveData<UiState<List<SpiritsSearchResult>>> get() = _state

    fun requestSearchApi(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val searchResult = searchAlcoholUseCase(query).toAlcoholDataList()
            val alcoholDataList = mutableListOf<AlcoholData>()
            alcoholDataList.addAll(searchResult)

            val spirit = alcoholDataList.firstOrNull()

            if (spirit != null) {
                val results = listOf(
                    SpiritsSearchResult(
                        name = spirit.name,
                        category = getCategory(spirit),
                        proof = spirit.abv,
                        image = spirit.imgUrl,
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
