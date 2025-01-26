package com.goody.dalda.network

import com.goody.dalda.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NaverSearchHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newBuilder = chain.request().newBuilder()
        newBuilder.addHeader(CLIENT_ID, BuildConfig.NAVER_CLIENT_ID).build()
        newBuilder.addHeader(CLIENT_SECRET, BuildConfig.NAVER_CLIENT_SECRET).build()
        newBuilder.addHeader(CONTENT_TYPE, CONTENT_TYPE_CONTENT).build()
        val request = newBuilder.build()
        return chain.proceed(request)
    }

    companion object {
        private const val CLIENT_ID = "X-Naver-Client-Id"
        private const val CLIENT_SECRET = "X-Naver-Client-Secret"
        private const val CONTENT_TYPE = "Content-Type"
        private const val CONTENT_TYPE_CONTENT = "application/json"
    }
}
