package com.goody.dalda.data.repository

interface LoginRepository {
    suspend fun login(accessToken: String): Boolean
}