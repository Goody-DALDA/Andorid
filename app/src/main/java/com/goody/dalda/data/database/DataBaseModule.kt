package com.goody.dalda.data.database

import android.content.Context
import androidx.room.Room
import com.goody.dalda.data.database.dao.BookmarkDao
import com.goody.dalda.data.database.dao.SearchDao
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
    fun provideBookmarkDatabase(@ApplicationContext context: Context): DaldaDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            DaldaDatabase::class.java,
            "dalda_database.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideBookmarkDao(daldaDatabase: DaldaDatabase): BookmarkDao {
        return daldaDatabase.BookmarkDao()
    }

    @Singleton
    @Provides
    fun provideSearchDao(daldaDatabase: DaldaDatabase): SearchDao {
        return daldaDatabase.SearchDao()
    }
}