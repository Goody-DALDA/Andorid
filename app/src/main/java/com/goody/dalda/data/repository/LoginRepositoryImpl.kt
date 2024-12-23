package com.goody.dalda.data.repository

import com.goody.dalda.data.dto.asDomain
import com.goody.dalda.data.local.PreferenceLocalDataSource
import com.goody.dalda.data.remote.UserRemoteDataSource
import com.goody.dalda.ui.model.Profile
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
    ): Boolean {
        return try {
            preferenceLocalDataSource.setAccessToken(token.accessToken)
            val response = userRemoteDataSource.login(nickname, email, profileImg)
            val accessToken = response.body()?.data ?: ""
            preferenceLocalDataSource.setAccessToken(accessToken)
            response.isSuccessful
        } catch (e: Exception) {
            preferenceLocalDataSource.setAccessToken("")
            false
        }
    }

    override suspend fun getProfile(): Profile {
        val response = userRemoteDataSource.fetchProfile()
        return response.body()!!.data.asDomain()
    }
}