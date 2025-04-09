package com.goody.dalda.ui.announcement

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.model.PostUIModel
import com.goody.dalda.data.model.toUIModel
import com.oyj.domain.usecase.FetchNoticeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnouncementViewModel @Inject constructor(
    private val fetchNoticeUseCase: FetchNoticeUseCase
) : ViewModel() {
    private val _posts = MutableStateFlow<List<PostUIModel>>(emptyList())
    val posts: StateFlow<List<PostUIModel>> = _posts

    init {
        fetchNoticePost()
    }

    private fun fetchNoticePost() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                fetchNoticeUseCase().collect {
                    _posts.value = it.toUIModel()
                }
            }.onFailure { throwable ->
                _posts.value = emptyList()
                Log.e(TAG, "fetchNoticePost: ${throwable.message}", )
            }
        }
    }
    companion object {
        private const val TAG = "AnnouncementViewModel"
    }
}
