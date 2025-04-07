package com.oyj.domain.repository

import com.oyj.domain.model.PostEntity

interface NoticeRepository {
    suspend fun fetchNotice(): List<PostEntity>
}