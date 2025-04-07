package com.goody.dalda.ui.liquor_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.model.AlcoholUIModel
import com.goody.dalda.data.model.BlogUIModel
import com.goody.dalda.data.model.toAppModelList
import com.goody.dalda.data.model.toDomain
import com.oyj.domain.usecase.bookmark.DeleteBookmarkAlcoholUseCase
import com.oyj.domain.usecase.GetBlogDataListUseCase
import com.oyj.domain.usecase.bookmark.InsertBookmarkAlcoholUseCase
import com.oyj.domain.usecase.bookmark.IsBookmarkAlcoholUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LiquorDetailsViewModel @Inject constructor(
    private val insertBookmarkAlcoholUseCase: InsertBookmarkAlcoholUseCase,
    private val deleteBookmarkAlcoholUseCase: DeleteBookmarkAlcoholUseCase,
    private val isBookmarkAlcoholUseCase: IsBookmarkAlcoholUseCase,
    private val getBlogDataListUseCase: GetBlogDataListUseCase
) : ViewModel() {
    private val _isBookmark = MutableStateFlow(false)
    val isBookmark: StateFlow<Boolean> = _isBookmark

    private val _blogUIModelList: MutableStateFlow<List<BlogUIModel>> = MutableStateFlow(emptyList())
    val blogUIModelList: StateFlow<List<BlogUIModel>> = _blogUIModelList

    private val _isDialogVisible = MutableStateFlow(false)
    val isDialogVisible: StateFlow<Boolean> = _isDialogVisible

    fun insertBookMark(alcoholUIModel: AlcoholUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            insertBookmarkAlcoholUseCase(alcoholUIModel.toDomain())
        }
    }

    fun deleteBookMark(alcoholUIModel: AlcoholUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteBookmarkAlcoholUseCase(alcoholUIModel.toDomain())
        }
    }

    fun setIsBookmark(alcoholUIModel: AlcoholUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            _isBookmark.value = isBookmarkAlcoholUseCase(alcoholUIModel.toDomain())
        }
    }

    fun setBookmark(isBookmark: Boolean) {
        _isBookmark.value = isBookmark
    }

    fun setDialogVisible(isVisible: Boolean) {
        _isDialogVisible.value = isVisible
    }

    fun fetchBlogDataList(alcoholUIModel: AlcoholUIModel) {
        val category = gerCategory(alcoholUIModel)
        val query = "${alcoholUIModel.name} $category".replace(TEXT_SPACE, TEXT_EMPTY)
        fetchBlogDataListByQuery(query)
    }

    private fun fetchBlogDataListByQuery(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _blogUIModelList.value = getBlogDataListUseCase(query).toAppModelList()
        }
    }

    private fun gerCategory(alcoholUIModel: AlcoholUIModel) = when (alcoholUIModel) {
        is AlcoholUIModel.Beer -> TEXT_BEER
        is AlcoholUIModel.Sake -> TEXT_SAKE
        is AlcoholUIModel.Soju -> TEXT_SOJU
        is AlcoholUIModel.TraditionalLiquor -> TEXT_TRADITIONAL_LIQUOR
        is AlcoholUIModel.Wine -> TEXT_WINE
        is AlcoholUIModel.Whisky -> TEXT_WHISKY
    }

    companion object {
        private const val TEXT_BEER = "맥주"
        private const val TEXT_SAKE = "사케"
        private const val TEXT_SOJU = "소주"
        private const val TEXT_TRADITIONAL_LIQUOR = "전통주"
        private const val TEXT_WINE = "와인"
        private const val TEXT_WHISKY = "위스키"
        private const val TEXT_SPACE = " "
        private const val TEXT_EMPTY = ""
    }
}
