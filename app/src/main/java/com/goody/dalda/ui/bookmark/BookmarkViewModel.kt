package com.goody.dalda.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.model.AlcoholUIModel
import com.goody.dalda.data.model.toUIModelList
import com.goody.dalda.data.model.toDomain
import com.oyj.domain.usecase.bookmark.DeleteBookmarkAlcoholUseCase
import com.oyj.domain.usecase.bookmark.GetBookmarkAlcoholListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getBookmarkAlcoholListUseCase: GetBookmarkAlcoholListUseCase,
    private val deleteBookmarkAlcoholUseCase: DeleteBookmarkAlcoholUseCase
) : ViewModel() {
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _bookmarkList = MutableStateFlow(emptyList<AlcoholUIModel>())
    val bookmarkList: StateFlow<List<AlcoholUIModel>> = _bookmarkList

    fun getBookmarkList() {
        viewModelScope.launch(Dispatchers.IO) {
            getBookmarkAlcoholListUseCase().collect{
                _bookmarkList.value = it.toUIModelList().reversed()
            }
        }
    }

    fun deleteBookMark(alcoholUIModel: AlcoholUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteBookmarkAlcoholUseCase(alcoholUIModel.toDomain())
            getBookmarkList()
        }
    }
}
