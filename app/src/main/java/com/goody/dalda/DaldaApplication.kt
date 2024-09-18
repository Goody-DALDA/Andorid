package com.goody.dalda

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DaldaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
