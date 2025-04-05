package com.oyj.data.source.remote

import com.oyj.data.dto.LeaveDto
import com.oyj.data.dto.LoginDto
import com.oyj.data.dto.LogoutDto
import com.oyj.data.dto.ProfileDto
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
