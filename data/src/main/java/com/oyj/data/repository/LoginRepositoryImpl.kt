package com.oyj.data.repository

import com.oyj.data.dto.toDomain
import com.oyj.data.dto.toResultMessageDomain
import com.oyj.data.source.local.PreferenceLocalDataSource
import com.oyj.data.source.remote.UserRemoteDataSource
import com.oyj.domain.model.OAuthTokenEntity
import com.oyj.domain.model.ProfileEntity
import com.oyj.domain.model.ResultMessageEntity
import com.oyj.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
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
    ): Flow<ProfileEntity?> {
        return flow {
            val response = userRemoteDataSource.login(nickname, email, profileImg)
            val loginDto = response.body()

            if (response.isSuccessful && loginDto != null) {
                val loginData = loginDto.data
                val accessToken = loginDto.data.accessToken
                preferenceLocalDataSource.setAccessToken(accessToken)

                emit(ProfileEntity(nickname, email, profileImg, loginData.isNewUser))
            } else {
                emit(null)
            }
        }.catch {
            preferenceLocalDataSource.setAccessToken("")
            emit(null)
        }
    }

    override suspend fun fetchProfile(): Flow<ProfileEntity> = flow {
        val response = userRemoteDataSource.fetchProfile()
        if (response.isSuccessful) {
            emit(response.body()!!.data?.toDomain() ?: ProfileEntity())
        } else {
            emit(ProfileEntity())
        }
    }


    override suspend fun logout(): Flow<ResultMessageEntity> {
        return flow {
            val response = userRemoteDataSource.logout()
            if (response.isSuccessful) {
                emit(
                    response.body()?.toResultMessageDomain() ?: ResultMessageEntity(
                        "failed",
                        "data null"
                    )
                )
            } else {
                emit(ResultMessageEntity("failed", "response not successful"))
            }
        }
    }

    override suspend fun leaveUser(): Flow<ResultMessageEntity> {
        return flow {
            val response = userRemoteDataSource.leaveUser()
            if (response.isSuccessful) {
                emit(
                    response.body()?.toResultMessageDomain() ?: ResultMessageEntity(
                        "failed",
                        "data null"
                    )
                )
            } else {
                emit(ResultMessageEntity("failed", "response not successful"))
            }
        }
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