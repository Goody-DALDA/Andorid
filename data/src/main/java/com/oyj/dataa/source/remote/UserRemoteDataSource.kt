package com.oyj.dataa.source.remote

import com.oyj.dataa.dto.LeaveDto
import com.oyj.dataa.dto.LoginDto
import com.oyj.dataa.dto.LogoutDto
import com.oyj.dataa.dto.ProfileDto
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
