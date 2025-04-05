package com.oyj.dataa.repository

import com.oyj.dataa.mapper.AlcoholDtoMapper.toDomain
import com.oyj.dataa.mapper.AlcoholDtoMapper.toResultMessageDomain
import com.oyj.dataa.source.local.PreferenceLocalDataSource
import com.oyj.dataa.source.remote.UserRemoteDataSource
import com.oyj.domain.model.OAuthTokenDomain
import com.oyj.domain.model.ProfileDomain
import com.oyj.domain.model.ResultMessageDomain
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
        token: OAuthTokenDomain,
    ): ProfileDomain? {
        return try {
            val response = userRemoteDataSource.login(nickname, email, profileImg)
            val loginDto = response.body()

            if (response.isSuccessful && loginDto != null) {
                val loginData = loginDto.data
                val accessToken = loginDto.data.accessToken
                preferenceLocalDataSource.setAccessToken(accessToken)

                ProfileDomain(nickname, email, profileImg, loginData.isNewUser)
            } else {
                null
            }
        } catch (e: Exception) {
            preferenceLocalDataSource.setAccessToken("")
            null
        }
    }

    override suspend fun getFetchProfile(): ProfileDomain {
        val response = userRemoteDataSource.fetchProfile()
        return response.body()!!.data?.toDomain() ?: ProfileDomain()
    }

    override suspend fun logout(): ResultMessageDomain {
        return userRemoteDataSource.logout().body()?.toResultMessageDomain()
            ?: ResultMessageDomain("failed", "data null")
    }

    override suspend fun leaveUser(): ResultMessageDomain {
        return userRemoteDataSource.leaveUser().body()?.toResultMessageDomain()
            ?: ResultMessageDomain("failed", "data null")
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

    override fun getProfile(): ProfileDomain {
        return preferenceLocalDataSource.getProfile()
    }

    override fun setProfile(profileDomain: ProfileDomain) {
        preferenceLocalDataSource.setProfile(profileDomain)
    }

    override fun clearProfile() {
        preferenceLocalDataSource.clearProfile()
    }
}