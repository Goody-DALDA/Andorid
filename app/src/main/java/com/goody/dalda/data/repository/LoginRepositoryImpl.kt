package com.goody.dalda.data.repository

import android.util.Log
import com.goody.dalda.data.dto.LeaveDto
import com.goody.dalda.data.dto.LogoutDto
import com.goody.dalda.data.dto.asDomain
import com.goody.dalda.data.local.PreferenceLocalDataSource
import com.goody.dalda.data.remote.UserRemoteDataSource
import com.goody.dalda.ui.model.Profile
import com.goody.dalda.util.PreferenceManager
import com.kakao.sdk.auth.model.OAuthToken
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val preferenceLocalDataSource: PreferenceLocalDataSource
): LoginRepository {
    override suspend fun login(
        nickname: String,
        email: String,
        profileImg: String,
        token: OAuthToken
    ): Profile? {
        return try {
            preferenceLocalDataSource.setAccessToken(token.accessToken)
            val response = userRemoteDataSource.login(nickname, email, profileImg)
            val loginDto = response.body()

            if (response.isSuccessful && loginDto != null) {
                val loginData = loginDto.data
                val accessToken = loginDto.data.accessToken
                preferenceLocalDataSource.setAccessToken(accessToken)

                Profile(nickname, email, profileImg, loginData.isNewUser)
            } else {
                null
            }
        } catch (e: Exception) {
            preferenceLocalDataSource.setAccessToken("")
            null
        }
    }

    override suspend fun getProfile(): Profile {
        val response = userRemoteDataSource.fetchProfile()
        return response.body()!!.data.asDomain()
    }

    override suspend fun logout(): LogoutDto {
        val response = userRemoteDataSource.logout()
        PreferenceManager.clearAccessToken()
        return response.body() ?: LogoutDto("failed", "data null")
    }

    override suspend fun leaveUser(): LeaveDto {
        val response = userRemoteDataSource.leaveUser()
        PreferenceManager.clearAccessToken()
        return response.body() ?: LeaveDto("failed", "data null")
    }
}