package com.oyj.data.repository

import android.util.Log
import com.oyj.data.source.remote.blog.NaverBlogDataSource
import com.oyj.domain.model.BlogEntity
import com.oyj.domain.repository.BlogRepository
import javax.inject.Inject

class BlogRepositoryImpl @Inject constructor(
    private val naverBlogDataSource: NaverBlogDataSource
) : BlogRepository {
    override suspend fun getBlogDataList(query: String): List<BlogEntity> {
        try {
            val blogSearchDto = naverBlogDataSource.getBlogData(query)

            return if (blogSearchDto.isSuccessful) {
                blogSearchDto.body()?.items?.map {
                    BlogEntity(
                        title = it.title.replace("<b>", "").replace("</b>", ""),
                        link = it.link,
                        description = it.description.replace("<b>", "").replace("</b>", ""),
                        bloggerName = it.bloggerName,
                        bloggerLink = it.bloggerLink,
                        postdate = it.postdate,
                    )
                } ?: emptyList()
            } else {
                Log.e(TAG, "getBlogDataList: ${blogSearchDto.errorBody()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e(TAG, "getBlogDataList: ${e.message}")
            return emptyList()
        }
    }

    private companion object {
        private const val TAG = "BlogRepositoryImpl"
    }
}