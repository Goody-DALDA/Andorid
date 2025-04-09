package com.goody.dalda.ui.label_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.model.AlcoholUIModel
import com.goody.dalda.data.AlcoholType
import com.goody.dalda.data.model.toUIModelList
import com.goody.dalda.ui.dialog.SpiritsSearchResult
import com.goody.dalda.ui.state.UiState
import com.oyj.domain.usecase.search.SearchAlcoholUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
            val searchResult: Flow<List<AlcoholUIModel>> = searchAlcoholUseCase(query).map { it.toUIModelList() }

            val alcoholUIModelList = mutableListOf<AlcoholUIModel>()
            searchResult.collect { alcoholUIModelList.addAll(it) }

            val spirit = alcoholUIModelList.firstOrNull()

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

    private fun getCategory(alcoholUIModel: AlcoholUIModel): String =
        when (alcoholUIModel) {
            is AlcoholUIModel.Soju -> AlcoholType.SOJU.alcoholName
            is AlcoholUIModel.Beer -> AlcoholType.BEER.alcoholName
            is AlcoholUIModel.Sake -> AlcoholType.SAKE.alcoholName
            is AlcoholUIModel.Wine -> AlcoholType.WINE.alcoholName
            is AlcoholUIModel.Whisky -> AlcoholType.WHISKY.alcoholName
            is AlcoholUIModel.TraditionalLiquor -> AlcoholType.TRADITIONALLIQUOR.alcoholName
        }
}
