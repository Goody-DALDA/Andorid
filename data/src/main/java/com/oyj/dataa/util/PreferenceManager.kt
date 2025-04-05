//package com.oyj.dataa.util
//
//import android.content.Context
//import android.content.SharedPreferences
//import com.oyj.dataa.model.Profile
//
//object PreferenceManager {
//    private const val ACCESS_TOKEN = "access_token"
//    private var pref: SharedPreferences? = null
//
//    fun init(context: Context) {
//        pref = context.getSharedPreferences("STATUS_PREFS", Context.MODE_PRIVATE)
//    }
//
//    fun updateShowOnboarding() {
//        pref?.edit()?.putBoolean("is_show_onboarding", true)?.apply()
//    }
//
//    fun isShowOnboarding(): Boolean = pref?.getBoolean("is_show_onboarding", false) ?: false
//
//    fun setAccessToken(token: String) {
//        pref?.edit()?.putString(ACCESS_TOKEN, token)?.apply()
//    }
//
//    fun getAccessToken(): String = pref?.getString(ACCESS_TOKEN, "") ?: ""
//
//    fun clearAccessToken() {
//        pref?.edit()?.putString(ACCESS_TOKEN, "")?.apply()
//    }
//
//    fun getProfile(): Profile =
//        Profile(
//            pref?.getString("nickname", "") ?: "",
//            pref?.getString("email", "") ?: "",
//            pref?.getString("profileImg", "") ?: "",
//        )
//
//    fun setProfile(profile: Profile) {
//        pref?.edit()?.putString("nickname", profile.nickname)?.apply()
//        pref?.edit()?.putString("email", profile.email)?.apply()
//        pref?.edit()?.putString("profileImg", profile.profileImg)?.apply()
//    }
//
//    fun clearProfile() {
//        pref?.edit()?.putString("nickname", "")?.apply()
//        pref?.edit()?.putString("email", "")?.apply()
//        pref?.edit()?.putString("profileImg", "")?.apply()
//    }
//}
