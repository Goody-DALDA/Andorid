package com.oyj.dataa.source.remote

import com.oyj.dataa.dto.NoticeDto
import retrofit2.Response

interface NoticeRemoteDataSource {
    suspend fun fetchNotice(): Response<NoticeDto>
}
