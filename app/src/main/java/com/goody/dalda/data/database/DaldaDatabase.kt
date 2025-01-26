package com.goody.dalda.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.goody.dalda.data.database.dao.BookmarkDao
import com.goody.dalda.data.database.dao.SearchDao
import com.goody.dalda.data.database.entity.BookmarkEntity
import com.goody.dalda.data.database.entity.SearchEntity

@Database(
    version = 1,
    entities = [
        BookmarkEntity::class,
        SearchEntity::class,
    ],
    exportSchema = true,
)
abstract class DaldaDatabase : RoomDatabase() {
    abstract fun BookmarkDao(): BookmarkDao

    abstract fun SearchDao(): SearchDao
}
