package com.goody.dalda.ui.label_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.ui.dialog.SpiritsSearchResult
import com.goody.dalda.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LabelSearchViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableLiveData<UiState<List<SpiritsSearchResult>>>(UiState.Uninitialized)
    val state: LiveData<UiState<List<SpiritsSearchResult>>> get() = _state

    fun requestSearchApi() {
        viewModelScope.launch(Dispatchers.IO) {
            val results = listOf(SpiritsSearchResult("대선", "소주", 20, ""))
            _state.postValue(UiState.Success(results))
        }
    }
}
