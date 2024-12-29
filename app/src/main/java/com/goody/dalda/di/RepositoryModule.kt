package com.goody.dalda.di

import com.goody.dalda.data.repository.LoginRepository
import com.goody.dalda.data.repository.LoginRepositoryImpl
import com.goody.dalda.data.repository.home.AlcoholRepository
import com.goody.dalda.data.repository.home.AlcoholRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindLoginRepository(repository: LoginRepositoryImpl): LoginRepository

    @Binds
    abstract fun bindAlcoholRepository(repository: AlcoholRepositoryImpl): AlcoholRepository
}
