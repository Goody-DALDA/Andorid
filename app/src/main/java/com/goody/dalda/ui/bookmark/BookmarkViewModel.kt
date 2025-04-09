package com.goody.dalda.ui.bookmark

import android.util.Log
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
    private val _bookmarkList = MutableStateFlow(emptyList<AlcoholUIModel>())
    val bookmarkList: StateFlow<List<AlcoholUIModel>> = _bookmarkList

    fun getBookmarkList() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                getBookmarkAlcoholListUseCase().collect{
                    _bookmarkList.value = it.toUIModelList().reversed()
                }
            }.onFailure {
                it.printStackTrace()
                Log.e(TAG, "getBookmarkList: ${it.message}", )
            }
        }
    }

    fun deleteBookMark(alcoholUIModel: AlcoholUIModel) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                deleteBookmarkAlcoholUseCase(alcoholUIModel.toDomain())
                getBookmarkList()
            }.onFailure {
                it.printStackTrace()
                Log.e(TAG, "deleteBookMark: ${it.message}", )
            }
        }
    }

    companion object{
        private const val TAG = "BookmarkViewModel"
    }
}
