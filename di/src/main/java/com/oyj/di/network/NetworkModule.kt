package com.oyj.di.network

import com.oyj.dataa.network.DaldaOkHttp
import com.oyj.dataa.network.DaldaRetrofit
import com.oyj.dataa.network.HeaderInterceptor
import com.oyj.dataa.network.NaverSearchHeaderInterceptor
import com.oyj.dataa.network.NaverSearchOkHttp
import com.oyj.dataa.network.NaverSearchRetrofit
import com.oyj.dataa.network.NaverSearchRetrofitService
import com.oyj.dataa.network.RetrofitService
import com.oyj.dataa.source.local.PreferenceLocalDataSource
import com.oyj.di.BuildConfig
import com.oyj.di.converter.DynamicConverterFactory
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
    fun provideHeaderInterceptor(
        preferenceLocalDataSource: PreferenceLocalDataSource
    ): HeaderInterceptor = HeaderInterceptor(preferenceLocalDataSource)


    @Singleton
    @Provides
    @DaldaOkHttp
    fun provideOkHttpClient(
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(getLoggingInterceptor())
        .addNetworkInterceptor(headerInterceptor)
        .build()

    @Singleton
    @Provides
    @NaverSearchOkHttp
    fun provideNaverSearchOkHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(getLoggingInterceptor())
        .addNetworkInterceptor(NaverSearchHeaderInterceptor())
        .build()

    @Singleton
    @Provides
    @DaldaRetrofit
    fun provideRetrofit(
        @DaldaOkHttp okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit
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
    ): Retrofit = Retrofit
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
