package com.goody.dalda.di

import com.goody.dalda.data.remote.blog.NaverBlogDataSource
import com.goody.dalda.data.remote.blog.NaverBlogDataSourceImpl
import com.goody.dalda.data.repository.LoginRepository
import com.goody.dalda.data.repository.LoginRepositoryImpl
import com.goody.dalda.data.repository.NoticeRepository
import com.goody.dalda.data.repository.NoticeRepositoryImpl
import com.goody.dalda.data.repository.blog.BlogRepository
import com.goody.dalda.data.repository.blog.BlogRepositoryImpl
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

    @Binds
    abstract fun bindNoticeRepository(repository: NoticeRepositoryImpl): NoticeRepository

    @Binds
    abstract fun bindBlogRepository(repository: BlogRepositoryImpl): BlogRepository
}
