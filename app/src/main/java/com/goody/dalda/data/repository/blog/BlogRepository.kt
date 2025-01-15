package com.goody.dalda.data.repository.blog

import com.goody.dalda.data.BlogData

interface BlogRepository {
    suspend fun getBlogDataList(query: String): List<BlogData>
}
