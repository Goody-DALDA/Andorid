package com.oyj.data.source.local

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.oyj.domain.model.ProfileEntity
import javax.inject.Inject

@SuppressLint("UseKtx")
class PreferenceLocalDataSourceImpl @Inject constructor(
    private val preferenceManager: SharedPreferences
) : PreferenceLocalDataSource {

    override fun updateShowOnboarding() {
        preferenceManager.edit()?.putBoolean("is_show_onboarding", true)?.apply()
    }

    override fun isShowOnboarding(): Boolean {
        return preferenceManager.getBoolean("is_show_onboarding", false)
    }

    override fun getAccessToken(): String {
        return preferenceManager.getString(ACCESS_TOKEN, "") ?: ""
    }

    override fun setAccessToken(token: String) {
        preferenceManager.edit()?.putString(ACCESS_TOKEN, token)?.apply()
    }

    override fun clearAccessToken() {
        preferenceManager.edit()?.putString(ACCESS_TOKEN, "")?.apply()
    }

    override fun getProfile(): ProfileEntity {
        return ProfileEntity(
            preferenceManager.getString("nickname", "") ?: "",
            preferenceManager.getString("email", "") ?: "",
            preferenceManager.getString("profileImg", "") ?: "",
        )
    }

    override fun setProfile(profileEntity: ProfileEntity) {
        preferenceManager.edit()?.putString("nickname", profileEntity.nickname)?.apply()
        preferenceManager.edit()?.putString("email", profileEntity.email)?.apply()
        preferenceManager.edit()?.putString("profileImg", profileEntity.profileImg)?.apply()
    }

    override fun clearProfile() {
        preferenceManager.edit()?.putString("nickname", "")?.apply()
        preferenceManager.edit()?.putString("email", "")?.apply()
        preferenceManager.edit()?.putString("profileImg", "")?.apply()
    }

    companion object {
        private val ACCESS_TOKEN = "access_token"
    }
}
