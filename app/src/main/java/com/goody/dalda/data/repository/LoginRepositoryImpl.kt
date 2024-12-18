package com.goody.dalda.data.repository

import com.goody.dalda.data.local.PreferenceLocalDataSource
import com.goody.dalda.data.remote.UserRemoteDataSource
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val preferenceLocalDataSource: PreferenceLocalDataSource
): LoginRepository {
    override suspend fun login(nickname: String, email: String, profileImg: String): Boolean {
        val response = userRemoteDataSource.login(nickname, email, profileImg)
        val accessToken = response.body()?.data?.accessToken ?: ""
        preferenceLocalDataSource.setAccessToken(accessToken)
        return response.isSuccessful
    }
}