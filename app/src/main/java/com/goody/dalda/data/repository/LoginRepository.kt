package com.goody.dalda.data.repository

import com.goody.dalda.ui.model.Profile

interface LoginRepository {
    suspend fun login(
        nickname: String,
        email: String,
        profileImg: String
    ): Boolean

    suspend fun getProfile(): Profile
}