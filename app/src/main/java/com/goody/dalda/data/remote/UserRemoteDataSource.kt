package com.goody.dalda.data.remote

import com.goody.dalda.data.dto.LoginDto
import retrofit2.Response

interface UserRemoteDataSource {
    suspend fun login(nickname: String, email: String, profileImg: String): Response<LoginDto>
}