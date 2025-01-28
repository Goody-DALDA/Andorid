package com.goody.dalda.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.repository.alcohol.AlcoholRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel
    @Inject
    constructor(
        private val alcoholRepository: AlcoholRepository,
    ) : ViewModel() {
        private val _query = MutableStateFlow("")
        val query: StateFlow<String> = _query

        private val _searchResultList = MutableStateFlow(emptyList<AlcoholData>())
        val searchResultList: StateFlow<List<AlcoholData>> = _searchResultList

        fun getBookmarkList() {
            viewModelScope.launch(Dispatchers.IO) {
                _searchResultList.value = alcoholRepository.getBookmarkAlcoholList().reversed()
            }
        }

        fun deleteBookMark(alcoholData: AlcoholData) {
            viewModelScope.launch(Dispatchers.IO) {
                alcoholRepository.deleteBookmarkAlcohol(alcoholData)
                getBookmarkList()
            }
        }
    }
