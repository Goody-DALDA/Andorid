package com.oyj.data.repository

import com.oyj.data.dto.toDomain
import com.oyj.data.source.remote.NoticeRemoteDataSource
import com.oyj.domain.model.PostEntity
import com.oyj.domain.repository.NoticeRepository
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(
    private val dataSource: NoticeRemoteDataSource
) : NoticeRepository {
    override suspend fun fetchNotice(): List<PostEntity> {
        return dataSource.fetchNotice().body()?.data?.map { it.toDomain() }
            ?: emptyList()
    }
}