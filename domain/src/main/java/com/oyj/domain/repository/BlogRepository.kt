package com.oyj.domain.repository

import com.oyj.domain.model.BlogDataDomain

interface BlogRepository {
    suspend fun getBlogDataList(query: String): List<BlogDataDomain>
}