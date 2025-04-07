package com.oyj.domain.repository

import com.oyj.domain.model.BlogEntity

interface BlogRepository {
    suspend fun getBlogDataList(query: String): List<BlogEntity>
}