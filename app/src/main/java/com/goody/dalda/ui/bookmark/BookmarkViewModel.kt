package com.goody.dalda.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.toDataModelList
import com.goody.dalda.data.toDomainModel
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

    private val _bookmarkList = MutableStateFlow(emptyList<AlcoholData>())
    val bookmarkList: StateFlow<List<AlcoholData>> = _bookmarkList

    fun getBookmarkList() {
        viewModelScope.launch(Dispatchers.IO) {
            _bookmarkList.value = getBookmarkAlcoholListUseCase().toDataModelList().reversed()
        }
    }

    fun deleteBookMark(alcoholData: AlcoholData) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteBookmarkAlcoholUseCase(alcoholData.toDomainModel())
            getBookmarkList()
        }
    }
}
