package com.oyj.domain.repository

import com.oyj.domain.model.BlogEntity
import kotlinx.coroutines.flow.Flow

interface BlogRepository {
    suspend fun getBlogDataList(query: String): Flow<List<BlogEntity>>
}