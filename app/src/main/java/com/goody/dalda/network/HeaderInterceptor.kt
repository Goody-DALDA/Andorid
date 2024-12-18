package com.goody.dalda.network

import com.goody.dalda.util.PreferenceManager
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newBuilder = chain.request().newBuilder()
        newBuilder.addHeader(CONTENT_TYPE, CONTENT_TYPE_CONTENT).build()
        newBuilder.addHeader(AUTHORIZATION, attachBearerString(PreferenceManager.getAccessToken())).build()

        val request = newBuilder.build()
        return chain.proceed(request)
    }

    private fun attachBearerString(token: String) = "Bearer ${token}"

    companion object {
        private const val CONTENT_TYPE = "Content-Type"
        private const val CONTENT_TYPE_CONTENT = "application/json"
        private const val AUTHORIZATION = "Authorization"
    }
}