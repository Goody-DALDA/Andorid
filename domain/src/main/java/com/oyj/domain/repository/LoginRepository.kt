package com.oyj.domain.repository

import com.oyj.domain.model.OAuthTokenEntity
import com.oyj.domain.model.ProfileEntity
import com.oyj.domain.model.ResultMessageEntity

interface LoginRepository {
    suspend fun login(
        nickname: String,
        email: String,
        profileImg: String,
        token: OAuthTokenEntity,
    ): ProfileEntity?

    suspend fun fetchProfile(): ProfileEntity

    suspend fun logout(): ResultMessageEntity

    suspend fun leaveUser(): ResultMessageEntity

    fun getOAuthToken(): String

    fun updateShowOnboarding()

    fun isShowOnboarding(): Boolean

    fun setAccessToken(token: String)

    fun clearAccessToken()

    fun getProfile(): ProfileEntity

    fun setProfile(profileEntity: ProfileEntity)

    fun clearProfile()
}