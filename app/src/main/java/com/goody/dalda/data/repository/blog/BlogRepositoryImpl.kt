package com.goody.dalda.data.repository.blog

import android.util.Log
import com.goody.dalda.data.BlogData
import com.goody.dalda.data.remote.blog.NaverBlogDataSource
import javax.inject.Inject

class BlogRepositoryImpl
@Inject
constructor(
    private val naverBlogDataSource: NaverBlogDataSource,
) : BlogRepository {
    override suspend fun getBlogDataList(query: String): List<BlogData> {
        val blogSearchDto = naverBlogDataSource.getBlogData(query)

        return if (blogSearchDto.isSuccessful) {
            blogSearchDto.body()?.items?.map {
                BlogData(
                    title = it.title.replace("<b>", "").replace("</b>", ""),
                    link = it.link,
                    description = it.description.replace("<b>", "").replace("</b>", ""),
                    bloggerName = it.bloggername,
                    bloggerLink = it.bloggerlink,
                    postdate = it.postdate,
                )
            } ?: emptyList()
        } else {
            Log.e(TAG, "getBlogDataList: ${blogSearchDto.errorBody()}")
            emptyList()
        }
    }

    companion object {
        private const val TAG = "BlogRepositoryImpl"
    }
}
