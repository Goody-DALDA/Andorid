package com.oyj.data.source.local

import com.oyj.domain.model.ProfileEntity

interface PreferenceLocalDataSource {
    fun updateShowOnboarding()

    fun isShowOnboarding(): Boolean

    fun getAccessToken(): String

    fun setAccessToken(token: String)

    fun clearAccessToken()

    fun getProfile(): ProfileEntity

    fun setProfile(profileEntity: ProfileEntity)

    fun clearProfile()
}
