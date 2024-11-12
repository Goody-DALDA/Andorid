package com.goody.dalda.ui.announcement

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnnouncementViewModel @Inject constructor() : ViewModel() {
    private val  list: List<Post> = listOf(
        Post("타이틀 입니다.", "2024.11.12"),
        Post("두번째 공지사항", "2024.10.22"),
        Post("3 공지사항", "2024.9.29"),
        Post("4 공지사항", "2024.8.18")
    )
    private val posts = mutableStateListOf<Post>()

    init {
        posts.addAll(list)
    }


    fun getNoticePosts() = posts
}

data class Post(val title: String, val date: String)
