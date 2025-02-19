package com.goody.dalda.data.repository

import com.goody.dalda.data.dto.asDomain
import com.goody.dalda.data.remote.NoticeRemoteDataSource
import com.goody.dalda.ui.model.Post
import javax.inject.Inject

class NoticeRepositoryImpl
@Inject
constructor(private val dataSource: NoticeRemoteDataSource) :
    NoticeRepository {
    override suspend fun fetchNotice(): List<Post> {
        val response = dataSource.fetchNotice()
        return response.body()?.data?.map { it.asDomain() } ?: emptyList()
    }
}
