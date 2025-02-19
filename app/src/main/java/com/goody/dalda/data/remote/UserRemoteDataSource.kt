package com.goody.dalda.data.remote

import com.goody.dalda.data.dto.LeaveDto
import com.goody.dalda.data.dto.LoginDto
import com.goody.dalda.data.dto.LogoutDto
import com.goody.dalda.data.dto.ProfileDto
import retrofit2.Response

interface UserRemoteDataSource {
    suspend fun login(
        nickname: String,
        email: String,
        profileImg: String,
    ): Response<LoginDto>

    suspend fun fetchProfile(): Response<ProfileDto>

    suspend fun logout(): Response<LogoutDto>

    suspend fun leaveUser(): Response<LeaveDto>
}
