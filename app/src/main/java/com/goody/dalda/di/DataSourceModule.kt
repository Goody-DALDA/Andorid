package com.goody.dalda.di

import com.goody.dalda.data.local.PreferenceLocalDataSource
import com.goody.dalda.data.local.PreferenceLocalDataSourceImpl
import com.goody.dalda.data.remote.UserRemoteDataSource
import com.goody.dalda.data.remote.UserRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindUserRemoteDataSource(dataSource: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    abstract fun bindPreferenceLocalDataSource(dataSource: PreferenceLocalDataSourceImpl): PreferenceLocalDataSource
}