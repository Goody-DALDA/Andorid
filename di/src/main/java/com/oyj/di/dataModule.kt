package com.oyj.di

import android.content.Context
import android.content.SharedPreferences
import com.oyj.dataa.database.DaldaDatabase
import com.oyj.dataa.database.dao.BookmarkDao
import com.oyj.dataa.database.dao.SearchDao
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideBookmarkDatabase(
        @ApplicationContext context: Context,
    ): DaldaDatabase {
        return Room.databaseBuilder(
            context = context.applicationContext,
            klass = DaldaDatabase::class.java,
            name = "dalda_database.db",
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

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences("STATUS_PREFS", Context.MODE_PRIVATE)
    }
}