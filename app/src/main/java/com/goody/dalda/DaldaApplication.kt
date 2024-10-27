package com.goody.dalda

import android.app.Application
import com.goody.dalda.util.PreferenceManager
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DaldaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
        PreferenceManager.init(this)
    }
}
