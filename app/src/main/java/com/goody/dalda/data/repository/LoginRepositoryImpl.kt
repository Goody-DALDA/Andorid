package com.goody.dalda.data.repository

import android.util.Log
import com.goody.dalda.data.remote.UserRemoteDataSource
import javax.inject.Inject


class LoginRepositoryImpl @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource): LoginRepository {
    override suspend fun login(accessToken: String): Boolean {
        val response = userRemoteDataSource.login(accessToken)

        if (response.isSuccessful) {
            Log.d("LoginRepositoryImpl", "kch jwtToken : ${response.body()?.data?.jwtToken}")
            Log.d("LoginRepositoryImpl", "kch refreshToken : ${response.body()?.data?.refreshToken}")
        }

        return response.isSuccessful
    }
}