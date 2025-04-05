package com.goody.dalda.ui.announcement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.ui.model.Post
import com.goody.dalda.ui.model.toAppModel
import com.oyj.domain.usecase.FetchNoticePostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnouncementViewModel @Inject constructor(
    private val fetchNoticePostUseCase: FetchNoticePostUseCase
) : ViewModel() {
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    init {
        fetchNoticePost()
    }

    private fun fetchNoticePost() {
        viewModelScope.launch(Dispatchers.IO) {
            _posts.value = fetchNoticePostUseCase().toAppModel()
        }
    }
}
