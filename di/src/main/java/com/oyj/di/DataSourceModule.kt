package com.oyj.di

import com.oyj.data.source.local.BookmarkLocalDataSource
import com.oyj.data.source.local.BookmarkLocalDataSourceImpl
import com.oyj.data.source.local.PreferenceLocalDataSource
import com.oyj.data.source.local.PreferenceLocalDataSourceImpl
import com.oyj.data.source.local.SearchLocalDataSource
import com.oyj.data.source.local.SearchLocalDataSourceImpl
import com.oyj.data.source.remote.NoticeRemoteDataSource
import com.oyj.data.source.remote.NoticeRemoteDataSourceImpl
import com.oyj.data.source.remote.UserRemoteDataSource
import com.oyj.data.source.remote.UserRemoteDataSourceImpl
import com.oyj.data.source.remote.blog.NaverBlogDataSource
import com.oyj.data.source.remote.blog.NaverBlogDataSourceImpl
import com.oyj.data.source.remote.home.AlcoholDataRemoteDataSource
import com.oyj.data.source.remote.home.AlcoholDataRemoteDataSourceImpl
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
    abstract fun bindAlcoholDataRemoteDataSource(repository: AlcoholDataRemoteDataSourceImpl): AlcoholDataRemoteDataSource

    @Binds
    abstract fun bindPreferenceLocalDataSource(dataSource: PreferenceLocalDataSourceImpl): PreferenceLocalDataSource

    @Binds
    abstract fun bindBookmarkDatabase(bookmarkLocalDataSource: BookmarkLocalDataSourceImpl): BookmarkLocalDataSource

    @Binds
    abstract fun bindNoticeRemoteDataSource(dataSource: NoticeRemoteDataSourceImpl): NoticeRemoteDataSource

    @Binds
    abstract fun bindNaverBlogDataSource(dataSource: NaverBlogDataSourceImpl): NaverBlogDataSource

    @Binds
    abstract fun bindSearchDataSource(dataSource: SearchLocalDataSourceImpl): SearchLocalDataSource
}
