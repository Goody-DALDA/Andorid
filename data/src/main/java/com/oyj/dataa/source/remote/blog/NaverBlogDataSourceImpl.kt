package com.oyj.dataa.source.remote.blog

import com.oyj.dataa.dto.blog.BlogSearchDto
import com.oyj.dataa.network.NaverSearchRetrofitService
import retrofit2.Response
import javax.inject.Inject

class NaverBlogDataSourceImpl @Inject constructor(
    private val service: NaverSearchRetrofitService
) : NaverBlogDataSource {
    override suspend fun getBlogData(query: String): Response<BlogSearchDto> {
        return service.searchBlog(query)
    }
}
