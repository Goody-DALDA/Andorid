package com.goody.dalda.ui.announcement

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.repository.NoticeRepository
import com.goody.dalda.ui.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(private val repository: NoticeRepository) :
    ViewModel() {

    private val _postState = mutableStateOf<Post?>(null)
    val postState: State<Post?> get() = _postState
    private val nextPostState = mutableStateOf<Post?>(null)
    private val prevPostState = mutableStateOf<Post?>(null)
    private val posts = mutableListOf<Post>()
    private var postIndex: Int = 0

    fun fetchNoticePost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            posts.addAll(repository.fetchNotice())
            setPost(post)
            setNextPost()
            setPrevPost()
        }
    }

    private fun setPost(post: Post) {
        _postState.value = post
        postIndex = posts.indexOf(post)
    }

    fun nextPost() {
        if (postIndex >= posts.size) return

        val post = posts[postIndex + 1]
        setPost(post)
        setNextPost()
        setPrevPost()
    }

    fun prevPost() {
        if (postIndex <= 0) return

        val post = posts[postIndex - 1]
        setPost(post)
        setNextPost()
        setPrevPost()
    }

    fun getNextPost() = nextPostState.value

    fun getPrevPost() = prevPostState.value

    private fun setNextPost() {
        nextPostState.value = if (postIndex >= posts.size - 1) null else posts[postIndex + 1]
    }

    private fun setPrevPost() {
        prevPostState.value = if (postIndex <= 0) null else posts[postIndex - 1]
    }
}