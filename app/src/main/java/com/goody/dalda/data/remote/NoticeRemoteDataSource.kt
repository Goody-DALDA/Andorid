package com.goody.dalda.data.remote

import com.goody.dalda.data.dto.NoticeDto
import retrofit2.Response

interface NoticeRemoteDataSource {

    suspend fun fetchNotice(): Response<NoticeDto>

}