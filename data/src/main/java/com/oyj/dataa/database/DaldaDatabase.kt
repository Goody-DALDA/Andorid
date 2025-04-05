package com.oyj.dataa.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oyj.dataa.database.dao.BookmarkDao
import com.oyj.dataa.database.dao.SearchDao
import com.oyj.dataa.database.entity.BookmarkEntity
import com.oyj.dataa.database.entity.SearchEntity

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
