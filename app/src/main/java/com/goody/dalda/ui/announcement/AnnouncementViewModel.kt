package com.goody.dalda.ui.announcement

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.repository.NoticeRepository
import com.goody.dalda.ui.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnouncementViewModel @Inject constructor(private val repository: NoticeRepository) :
    ViewModel() {
    private val posts = mutableStateListOf<Post>()

    init {
        fetchNoticePost()
    }

    private fun fetchNoticePost() {
        viewModelScope.launch(Dispatchers.IO) {
            posts.addAll(repository.fetchNotice())
        }
    }


    fun getNoticePosts() = posts
}
