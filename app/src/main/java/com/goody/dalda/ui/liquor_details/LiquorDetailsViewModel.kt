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
class LiquorDetailsViewModel
@Inject
constructor(
    private val alcoholRepository: AlcoholRepository,
    private val blogRepository: BlogRepository,
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

    private fun fetchBlogDataList(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _blogDataList.value =
                blogRepository.getBlogDataList(query.replace(TEXT_SPACE, TEXT_EMPTY))
        }
    }

    fun fetchBlogDataList(alcoholData: AlcoholData) {
        val category =
            when (alcoholData) {
                is AlcoholData.Beer -> TEXT_BEER
                is AlcoholData.Sake -> TEXT_SAKE
                is AlcoholData.Soju -> TEXT_SOJU
                is AlcoholData.TraditionalLiquor -> TEXT_TRADITIONAL_LIQUOR
                is AlcoholData.Wine -> TEXT_WINE
                is AlcoholData.Wisky -> TEXT_WISKY
            }
        val query = "${alcoholData.name} $category"
        fetchBlogDataList(query)
    }

    companion object {
        private const val TEXT_BEER = "맥주"
        private const val TEXT_SAKE = "사케"
        private const val TEXT_SOJU = "소주"
        private const val TEXT_TRADITIONAL_LIQUOR = "전통주"
        private const val TEXT_WINE = "와인"
        private const val TEXT_WISKY = "위스키"
        private const val TEXT_SPACE = " "
        private const val TEXT_EMPTY = ""
    }
}
