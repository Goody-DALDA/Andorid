package com.goody.dalda.data.remote.blog

import com.goody.dalda.data.dto.blog.BlogSearchDto
import com.goody.dalda.network.NaverSearchRetrofitService
import retrofit2.Response
import javax.inject.Inject

class NaverBlogDataSourceImpl @Inject constructor(
    private val service: NaverSearchRetrofitService
) :
    NaverBlogDataSource {
    override suspend fun getBlogData(query: String): Response<BlogSearchDto> {
        return service.searchBlog(query)
    }
}
