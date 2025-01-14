package com.goody.dalda.ui.liquor_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.BlogData
import com.goody.dalda.data.repository.blog.BlogRepository
import com.goody.dalda.data.repository.home.AlcoholRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LiquorDetailsViewModel @Inject constructor(
    private val alcoholRepository: AlcoholRepository,
    private val blogRepository: BlogRepository
) : ViewModel() {

    private val _isBookmark = MutableStateFlow(false)
    val isBookmark: StateFlow<Boolean> = _isBookmark

    private val _blogDataList: MutableStateFlow<List<BlogData>> = MutableStateFlow(emptyList())
    val blogDataList: StateFlow<List<BlogData>> = _blogDataList

    fun insertBookMark(alcoholData: AlcoholData) {
        viewModelScope.launch(Dispatchers.IO) {
            alcoholRepository.insertBookmarkAlcohol(alcoholData)
        }
    }

    fun deleteBookMark(alcoholData: AlcoholData) {
        viewModelScope.launch(Dispatchers.IO) {
            alcoholRepository.deleteBookmarkAlcohol(alcoholData)
        }
    }

    fun setIsBookmark(alcoholData: AlcoholData) {
        viewModelScope.launch(Dispatchers.IO) {
            _isBookmark.value = alcoholRepository.isBookmarkAlcohol(alcoholData)
        }
    }

    fun setBookmark(isBookmark: Boolean) {
        _isBookmark.value = isBookmark
    }

    fun fetchBlogDataList(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _blogDataList.value = blogRepository.getBlogDataList(query)
        }
    }
}