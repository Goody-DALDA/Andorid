package com.oyj.di

import com.oyj.data.repository.BlogRepositoryImpl
import com.oyj.data.repository.DataAlcoholRepositoryImpl
import com.oyj.data.repository.DataSearchRepositoryImpl
import com.oyj.data.repository.LoginRepositoryImpl
import com.oyj.data.repository.NoticeRepositoryImpl
import com.oyj.data.repository.alcohol.AlcoholRepositoryImpl
import com.oyj.domain.repository.AlcoholRepository
import com.oyj.domain.repository.BlogRepository
import com.oyj.domain.repository.DataAlcoholRepository
import com.oyj.domain.repository.DataSearchRepository
import com.oyj.domain.repository.LoginRepository
import com.oyj.domain.repository.NoticeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindDataAlcoholRepository(dataAlcoholRepository: DataAlcoholRepositoryImpl): DataAlcoholRepository

    @Binds
    abstract fun bindDataSearchRepository(dataSearchRepository: DataSearchRepositoryImpl): DataSearchRepository

    @Binds
    abstract fun bindLoginRepository(repository: LoginRepositoryImpl): LoginRepository

    @Binds
    abstract fun bindAlcoholRepository(repository: AlcoholRepositoryImpl): AlcoholRepository

    @Binds
    abstract fun bindNoticeRepository(repository: NoticeRepositoryImpl): NoticeRepository

    @Binds
    abstract fun bindBlogRepository(repository: BlogRepositoryImpl): BlogRepository
}
