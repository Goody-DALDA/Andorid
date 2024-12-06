package com.goody.dalda.data.remote

import com.goody.dalda.data.dto.LoginDto
import retrofit2.Response

interface UserRemoteDataSource {
    suspend fun login(accessToken: String): Response<LoginDto>
}