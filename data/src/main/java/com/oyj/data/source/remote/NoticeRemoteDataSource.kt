package com.oyj.data.source.remote

import com.oyj.data.dto.NoticeDto
import retrofit2.Response

interface NoticeRemoteDataSource {
    suspend fun fetchNotice(): Response<NoticeDto>
}
