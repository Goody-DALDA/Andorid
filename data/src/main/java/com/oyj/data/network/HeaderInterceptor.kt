package com.oyj.data.network

import com.oyj.data.source.local.PreferenceLocalDataSource
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(
    private val preferenceLocalDataSource: PreferenceLocalDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newBuilder = chain.request().newBuilder()
        newBuilder.addHeader(CONTENT_TYPE, CONTENT_TYPE_CONTENT).build()
        newBuilder.addHeader(AUTHORIZATION, attachBearerString(preferenceLocalDataSource.getAccessToken()))
            .build()

        val request = newBuilder.build()
        return chain.proceed(request)
    }

    private fun attachBearerString(token: String) = "Bearer $token"

    companion object {
        private const val CONTENT_TYPE = "Content-Type"
        private const val CONTENT_TYPE_CONTENT = "application/json"
        private const val AUTHORIZATION = "Authorization"
    }
}
