package com.oyj.dataa.source.local

import com.oyj.domain.model.ProfileDomain

interface PreferenceLocalDataSource {
    fun updateShowOnboarding()

    fun isShowOnboarding(): Boolean

    fun getAccessToken(): String

    fun setAccessToken(token: String)

    fun clearAccessToken()

    fun getProfile(): ProfileDomain

    fun setProfile(profileDomain: ProfileDomain)

    fun clearProfile()
}
