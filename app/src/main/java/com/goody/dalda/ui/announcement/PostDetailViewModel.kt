package com.goody.dalda.ui.announcement

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
class PostDetailViewModel
    @Inject
    constructor(
        private val repository: NoticeRepository,
    ) : ViewModel() {
        private val _currentPost = MutableStateFlow<Post?>(null)
        val currentPost: StateFlow<Post?> = _currentPost

        private val _nextPost = MutableStateFlow<Post?>(null)
        val nextPost: StateFlow<Post?> = _nextPost

        private val _prevPost = MutableStateFlow<Post?>(null)
        val prevPost: StateFlow<Post?> = _prevPost

        private var postList: List<Post> = emptyList()

        private var currentIndex = 0

        fun fetchNoticePost(post: Post) {
            viewModelScope.launch(Dispatchers.IO) {
                postList = repository.fetchNotice()
                setPost(post)
                setNextPost()
                setPrevPost()
            }
        }

        private fun setPost(post: Post) {
            _currentPost.value = post
            currentIndex = postList.indexOf(post)
        }

        fun nextPost() {
            if (currentIndex >= postList.size - 1) return
            val post = postList[currentIndex + 1]
            setPost(post)
            setNextPost()
            setPrevPost()
        }

        fun prevPost() {
            if (currentIndex <= 0) return

            val post = postList[currentIndex - 1]
            setPost(post)
            setNextPost()
            setPrevPost()
        }

        private fun setNextPost() {
            _nextPost.value =
                if (currentIndex >= postList.size - 1) null else postList[currentIndex + 1]
        }

        private fun setPrevPost() {
            _prevPost.value = if (currentIndex <= 0) null else postList[currentIndex - 1]
        }
    }
