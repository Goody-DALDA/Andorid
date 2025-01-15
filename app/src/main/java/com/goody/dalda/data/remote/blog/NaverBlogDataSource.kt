package com.goody.dalda.data.remote.blog

import com.goody.dalda.data.dto.blog.BlogSearchDto
import retrofit2.Response

interface NaverBlogDataSource {
    suspend fun getBlogData(query: String): Response<BlogSearchDto>
}