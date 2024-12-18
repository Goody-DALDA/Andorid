package com.goody.dalda.data.repository

import com.goody.dalda.data.remote.UserRemoteDataSource
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource): LoginRepository {
    override suspend fun login(nickname: String, email: String, profileImg: String): Boolean {
        val response = userRemoteDataSource.login(nickname, email, profileImg)

        response.body()?.data?.accessToken
        return response.isSuccessful
    }
}