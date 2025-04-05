package com.oyj.dataa.network

import com.oyj.dataa.dto.blog.BlogSearchDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverSearchRetrofitService {
    @GET("blog.json")
    suspend fun searchBlog(
        @Query("query") query: String,
    ): Response<BlogSearchDto>
}
