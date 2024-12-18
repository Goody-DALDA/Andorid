package com.goody.dalda.data.local

import com.goody.dalda.util.PreferenceManager
import javax.inject.Inject


class PreferenceLocalDataSourceImpl @Inject constructor(): PreferenceLocalDataSource {

    override fun getAccessToken(): String {
        return PreferenceManager.getAccessToken()
    }

    override fun setAccessToken(token: String) {
        PreferenceManager.setAccessToken(token)
    }
}