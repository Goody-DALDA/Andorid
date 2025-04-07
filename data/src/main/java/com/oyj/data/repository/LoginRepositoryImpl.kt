package com.oyj.data.repository

import com.oyj.data.dto.toDomain
import com.oyj.data.dto.toResultMessageDomain
import com.oyj.data.source.local.PreferenceLocalDataSource
import com.oyj.data.source.remote.UserRemoteDataSource
import com.oyj.domain.model.OAuthTokenEntity
import com.oyj.domain.model.ProfileEntity
import com.oyj.domain.model.ResultMessageEntity
import com.oyj.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val preferenceLocalDataSource: PreferenceLocalDataSource,
) : LoginRepository {
    override suspend fun login(
        nickname: String,
        email: String,
        profileImg: String,
        token: OAuthTokenEntity,
    ): ProfileEntity? {
        return try {
            val response = userRemoteDataSource.login(nickname, email, profileImg)
            val loginDto = response.body()

            if (response.isSuccessful && loginDto != null) {
                val loginData = loginDto.data
                val accessToken = loginDto.data.accessToken
                preferenceLocalDataSource.setAccessToken(accessToken)

                ProfileEntity(nickname, email, profileImg, loginData.isNewUser)
            } else {
                null
            }
        } catch (e: Exception) {
            preferenceLocalDataSource.setAccessToken("")
            null
        }
    }

    override suspend fun fetchProfile(): ProfileEntity {
        val response = userRemoteDataSource.fetchProfile()
        return response.body()!!.data?.toDomain() ?: ProfileEntity()
    }

    override suspend fun logout(): ResultMessageEntity {
        return userRemoteDataSource.logout().body()?.toResultMessageDomain()
            ?: ResultMessageEntity("failed", "data null")
    }

    override suspend fun leaveUser(): ResultMessageEntity {
        return userRemoteDataSource.leaveUser().body()?.toResultMessageDomain()
            ?: ResultMessageEntity("failed", "data null")
    }

    override fun getOAuthToken(): String {
       return preferenceLocalDataSource.getAccessToken()
    }

    override fun updateShowOnboarding() {
        preferenceLocalDataSource.updateShowOnboarding()
    }

    override fun isShowOnboarding(): Boolean {
        return preferenceLocalDataSource.isShowOnboarding()
    }

    override fun setAccessToken(token: String) {
        preferenceLocalDataSource.setAccessToken(token)
    }

    override fun clearAccessToken() {
        preferenceLocalDataSource.clearAccessToken()
    }

    override fun getProfile(): ProfileEntity {
        return preferenceLocalDataSource.getProfile()
    }

    override fun setProfile(profileEntity: ProfileEntity) {
        preferenceLocalDataSource.setProfile(profileEntity)
    }

    override fun clearProfile() {
        preferenceLocalDataSource.clearProfile()
    }
}