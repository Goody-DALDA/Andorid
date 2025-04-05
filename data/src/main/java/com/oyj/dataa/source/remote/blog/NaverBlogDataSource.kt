package com.oyj.dataa.source.remote.blog

import com.oyj.dataa.dto.blog.BlogSearchDto
import retrofit2.Response

interface NaverBlogDataSource {
    suspend fun getBlogData(query: String): Response<BlogSearchDto>
}
