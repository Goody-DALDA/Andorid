package com.oyj.di

import com.oyj.dataa.repository.DataAlcoholRepositoryImpl
import com.oyj.dataa.repository.DataSearchRepositoryImpl
import com.oyj.domain.repository.DataAlcoholRepository
import com.oyj.domain.repository.DataSearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindDataAlcoholRepository(dataAlcoholRepository: DataAlcoholRepositoryImpl): DataAlcoholRepository

    @Binds
    abstract fun bindDataSearchRepository(dataSearchRepository: DataSearchRepositoryImpl): DataSearchRepository
}