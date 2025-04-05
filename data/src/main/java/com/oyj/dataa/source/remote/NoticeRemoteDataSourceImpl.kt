package com.oyj.dataa.source.remote

import com.oyj.dataa.dto.NoticeDto
import com.oyj.dataa.network.RetrofitService
import retrofit2.Response
import javax.inject.Inject

class NoticeRemoteDataSourceImpl @Inject constructor(
    private val service: RetrofitService
) : NoticeRemoteDataSource {
    override suspend fun fetchNotice(): Response<NoticeDto> {
        return service.fetchNotice()
    }
}
