package com.goody.dalda.data.repository

interface LoginRepository {
    suspend fun login(
        nickname: String,
        email: String,
        profileImg: String
    ): Boolean
}