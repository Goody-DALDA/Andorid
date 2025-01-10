package com.goody.dalda.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.goody.dalda.data.database.dao.BookmarkDao
import com.goody.dalda.data.database.entity.BookmarkEntity

@Database(
    entities = [BookmarkEntity::class],
    version = 1
)
abstract class BookmarkDatabase : RoomDatabase() {
    abstract fun BookmarkDao(): BookmarkDao
}