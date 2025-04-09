package com.oyj.domain.repository

import com.oyj.domain.model.PostEntity
import kotlinx.coroutines.flow.Flow

interface NoticeRepository {
    suspend fun fetchNotice(): Flow<List<PostEntity>>
}