package com.oyj.data.source.remote.blog

import com.oyj.data.dto.blog.BlogSearchDto
import com.oyj.data.network.NaverSearchRetrofitService
import retrofit2.Response
import javax.inject.Inject

class NaverBlogDataSourceImpl @Inject constructor(
    private val service: NaverSearchRetrofitService
) : NaverBlogDataSource {
    override suspend fun getBlogData(query: String): Response<BlogSearchDto> {
        return service.searchBlog(query)
    }
}
