package com.oyj.data.source.remote.blog

import com.oyj.data.dto.blog.BlogSearchDto
import retrofit2.Response

interface NaverBlogDataSource {
    suspend fun getBlogData(query: String): Response<BlogSearchDto>
}
