package com.goody.dalda.network

import com.goody.dalda.BuildConfig
import com.goody.dalda.data.converter.DynamicConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private fun getLoggingInterceptor(): HttpLoggingInterceptor =
    if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    } else {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }


    @Singleton
    @Provides
    @DaldaOkHttp
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(getLoggingInterceptor())
            .addNetworkInterceptor(HeaderInterceptor())
            .build()

    @Singleton
    @Provides
    @NaverSearchOkHttp
    fun provideNaverSearchOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(getLoggingInterceptor())
            .addNetworkInterceptor(NaverSearchHeaderInterceptor())
            .build()

    @Singleton
    @Provides
    @DaldaRetrofit
    fun provideRetrofit(
        @DaldaOkHttp okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.SERVER_API_URL)
            .addConverterFactory(DynamicConverterFactory())
            .build()

    @Singleton
    @Provides
    @NaverSearchRetrofit
    fun provideNaverSearchRetrofit(
        @NaverSearchOkHttp okHttpClient: OkHttpClient,
    ): Retrofit =
        Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.NAVER_SEARCH_API_URL)
            .addConverterFactory(DynamicConverterFactory())
            .build()

    @Provides
    @Singleton
    fun provideRetrofitService(
        @DaldaRetrofit retrofit: Retrofit,
    ): RetrofitService = retrofit.create(RetrofitService::class.java)

    @Provides
    @Singleton
    fun provideNaverSearchRetrofitService(
        @NaverSearchRetrofit retrofit: Retrofit,
    ): NaverSearchRetrofitService = retrofit.create(NaverSearchRetrofitService::class.java)
}
