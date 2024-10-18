package com.goody.dalda.ui.label_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LabelSearchViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableLiveData<UiState<Unit>>(UiState.Uninitialized)
    val state: LiveData<UiState<Unit>> get() = _state

    fun requestSearchApi() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.postValue(UiState.Empty)
        }
    }
}