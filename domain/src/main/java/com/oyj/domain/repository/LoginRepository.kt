package com.oyj.domain.repository

import com.oyj.domain.model.OAuthTokenDomain
import com.oyj.domain.model.ProfileDomain
import com.oyj.domain.model.ResultMessageDomain

interface LoginRepository {
    suspend fun login(
        nickname: String,
        email: String,
        profileImg: String,
        token: OAuthTokenDomain,
    ): ProfileDomain?

    suspend fun getFetchProfile(): ProfileDomain

    suspend fun logout(): ResultMessageDomain

    suspend fun leaveUser(): ResultMessageDomain

    fun getOAuthToken(): String

    fun updateShowOnboarding()

    fun isShowOnboarding(): Boolean

    fun setAccessToken(token: String)

    fun clearAccessToken()

    fun getProfile(): ProfileDomain

    fun setProfile(profileDomain: ProfileDomain)

    fun clearProfile()
}