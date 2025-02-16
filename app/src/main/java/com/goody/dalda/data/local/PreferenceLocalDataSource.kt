package com.goody.dalda.data.local

interface PreferenceLocalDataSource {
    fun getAccessToken(): String

    fun setAccessToken(token: String)
}
