package com.goody.dalda.data.repository

import com.goody.dalda.data.dto.LeaveDto
import com.goody.dalda.data.dto.LogoutDto
import com.goody.dalda.ui.model.Profile
import com.kakao.sdk.auth.model.OAuthToken

interface LoginRepository {
    suspend fun login(
        nickname: String,
        email: String,
        profileImg: String,
        token: OAuthToken,
    ): Profile?

    suspend fun getProfile(): Profile

    suspend fun logout(): LogoutDto

    suspend fun leaveUser(): LeaveDto
}
