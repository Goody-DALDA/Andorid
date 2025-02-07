package com.goody.dalda.ui.announcement

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.repository.NoticeRepository
import com.goody.dalda.ui.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnouncementViewModel
@Inject
constructor(private val repository: NoticeRepository) :
    ViewModel() {
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts : StateFlow<List<Post>> = _posts

    init {
        fetchNoticePost()
    }

    private fun fetchNoticePost() {
        viewModelScope.launch(Dispatchers.IO) {
            _posts.value = repository.fetchNotice()
        }
    }
}
