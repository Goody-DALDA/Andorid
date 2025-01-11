package com.goody.dalda.ui.liquor_details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.repository.home.AlcoholRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LiquorDetailsViewModel @Inject constructor(val repository: AlcoholRepository) : ViewModel() {

    private val _isBookmark = MutableStateFlow(false)
    val isBookmark: StateFlow<Boolean> = _isBookmark

    fun insertBookMark(alcoholData: AlcoholData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertBookmarkAlcohol(alcoholData)
        }
    }

    fun deleteBookMark(alcoholData: AlcoholData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBookmarkAlcohol(alcoholData)
        }
    }

    fun setIsBookmark(alcoholData: AlcoholData) {
        viewModelScope.launch(Dispatchers.IO) {
            _isBookmark.value = repository.isBookmarkAlcohol(alcoholData)
        }
    }

    fun setBookmark(isBookmark: Boolean) {
        _isBookmark.value = isBookmark
    }
}