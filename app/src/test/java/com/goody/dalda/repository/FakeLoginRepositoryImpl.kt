package com.goody.dalda.repository

import com.goody.dalda.data.dto.LeaveDto
import com.goody.dalda.data.dto.LogoutDto
import com.goody.dalda.data.dto.asDomain
import com.goody.dalda.data.remote.UserRemoteDataSource
import com.goody.dalda.data.repository.LoginRepository
import com.goody.dalda.ui.model.Profile
import com.kakao.sdk.auth.model.OAuthToken

class FakeLoginRepositoryImpl(
    private val userRemoteDataSource: UserRemoteDataSource,
) : LoginRepository {
    override suspend fun login(
        nickname: String,
        email: String,
        profileImg: String,
        token: OAuthToken
    ): Profile? {
        TODO("Not yet implemented")
    }

    override suspend fun getProfile(): Profile {
        val response = userRemoteDataSource.fetchProfile()
        return response.body()!!.data.asDomain()
    }

    override suspend fun logout(): LogoutDto {
        return userRemoteDataSource.logout().body() ?: LogoutDto("failed", "data null")
    }

    override suspend fun leaveUser(): LeaveDto {
        return userRemoteDataSource.leaveUser().body() ?: LeaveDto("failed", "data null")
    }
}