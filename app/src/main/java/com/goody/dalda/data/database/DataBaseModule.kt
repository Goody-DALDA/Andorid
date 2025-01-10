package com.goody.dalda.data.database

import android.content.Context
import androidx.room.Room
import com.goody.dalda.data.database.dao.BookmarkDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideBookmarkDatabase(@ApplicationContext context: Context): BookmarkDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            BookmarkDatabase::class.java,
            "bookmark_database.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideBookmarkDao(bookmarkDatabase: BookmarkDatabase): BookmarkDao {
        return bookmarkDatabase.BookmarkDao()
    }
}