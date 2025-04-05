package com.oyj.dataa.repository

import com.oyj.dataa.mapper.AlcoholDtoMapper.toPostDomain
import com.oyj.dataa.source.remote.NoticeRemoteDataSource
import com.oyj.domain.model.PostDomain
import com.oyj.domain.repository.NoticeRepository
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(
    private val dataSource: NoticeRemoteDataSource
) : NoticeRepository {
    override suspend fun fetchNotice(): List<PostDomain> {
        return dataSource.fetchNotice().body()?.data?.map { it.toPostDomain() }
            ?: emptyList()
    }
}