package com.goody.dalda.util

import android.content.Context
import android.content.SharedPreferences

object PreferenceManager {
    private const val ACCESS_TOKEN = "access_token"

    private var pref: SharedPreferences? = null

    fun init(context: Context) {
        pref = context.getSharedPreferences("STATUS_PREFS", Context.MODE_PRIVATE)
    }

    fun updateShowOnboarding() {
        pref?.edit()?.putBoolean("is_show_onboarding", true)?.apply()
    }

    fun isShowOnboarding(): Boolean {
        return pref?.getBoolean("is_show_onboarding", false) ?: false
    }

    fun setAccessToken(token: String) {
        pref?.edit()?.putString(ACCESS_TOKEN, token)?.apply()
    }

    fun getAccessToken(): String {
        return pref?.getString(ACCESS_TOKEN, "") ?: ""
    }

    fun clearAccessToken() {
        pref?.edit()?.putString(ACCESS_TOKEN, "")?.apply()
    }
}
