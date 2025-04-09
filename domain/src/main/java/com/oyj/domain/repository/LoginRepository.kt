package com.oyj.domain.repository

import com.oyj.domain.model.OAuthTokenEntity
import com.oyj.domain.model.ProfileEntity
import com.oyj.domain.model.ResultMessageEntity
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(
        nickname: String,
        email: String,
        profileImg: String,
        token: OAuthTokenEntity,
    ): Flow<ProfileEntity?>

    suspend fun fetchProfile(): Flow<ProfileEntity>

    suspend fun logout(): Flow<ResultMessageEntity>

    suspend fun leaveUser(): Flow<ResultMessageEntity>

    fun getOAuthToken(): String

    fun updateShowOnboarding()

    fun isShowOnboarding(): Boolean

    fun setAccessToken(token: String)

    fun clearAccessToken()

    fun getProfile(): ProfileEntity

    fun setProfile(profileEntity: ProfileEntity)

    fun clearProfile()
}