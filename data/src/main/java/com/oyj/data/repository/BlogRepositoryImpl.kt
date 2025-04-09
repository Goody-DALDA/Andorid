package com.oyj.data.repository

import android.util.Log
import com.oyj.data.source.remote.blog.NaverBlogDataSource
import com.oyj.domain.model.BlogEntity
import com.oyj.domain.repository.BlogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BlogRepositoryImpl @Inject constructor(
    private val naverBlogDataSource: NaverBlogDataSource
) : BlogRepository {
    override suspend fun getBlogDataList(query: String): Flow<List<BlogEntity>> = flow {
        runCatching {
            val response = naverBlogDataSource.getBlogData(query)
            if (response.isSuccessful) {
                response.body()?.items?.map {
                    BlogEntity(
                        title = it.title.replace("<b>", "").replace("</b>", ""),
                        link = it.link,
                        description = it.description.replace("<b>", "").replace("</b>", ""),
                        bloggerName = it.bloggerName,
                        bloggerLink = it.bloggerLink,
                        postdate = it.postdate,
                    )
                }?.let { emit(it) } ?: emit(emptyList())
            } else {
                Log.e(TAG, "getBlogDataList: ${response.message()}")
                emit(emptyList())
            }
        }.onFailure { throwable ->
            Log.e(TAG, "Failed to get blog data", throwable)
            emit(emptyList())
        }
    }

    private companion object {
        private const val TAG = "BlogRepositoryImpl"
    }
}