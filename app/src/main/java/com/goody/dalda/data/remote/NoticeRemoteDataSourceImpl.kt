package com.goody.dalda.data.remote

import com.goody.dalda.data.dto.NoticeDto
import com.goody.dalda.network.RetrofitService
import retrofit2.Response
import javax.inject.Inject

class NoticeRemoteDataSourceImpl @Inject constructor(private val service: RetrofitService): NoticeRemoteDataSource {
    override suspend fun fetchNotice(): Response<NoticeDto> {
        return service.fetchNotice()
    }
}