package com.oyj.data.source.remote

import com.oyj.data.dto.NoticeDto
import com.oyj.data.network.RetrofitService
import retrofit2.Response
import javax.inject.Inject

class NoticeRemoteDataSourceImpl @Inject constructor(
    private val service: RetrofitService
) : NoticeRemoteDataSource {
    override suspend fun fetchNotice(): Response<NoticeDto> {
        return service.fetchNotice()
    }
}
