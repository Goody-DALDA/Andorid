package com.goody.dalda.data.repository

import com.goody.dalda.ui.model.Post

interface NoticeRepository {
    suspend fun fetchNotice(): List<Post>
}