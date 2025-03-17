package com.goody.dalda.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.goody.dalda.data.database.entity.BookmarkEntity

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAlcohol(bookmarkEntity: BookmarkEntity)

    @Delete
    suspend fun deleteAlcohol(bookmarkEntity: BookmarkEntity)

    @Query("SELECT * FROM bookmarkRepo")
    suspend fun getAllBookMark(): List<BookmarkEntity>

    @Query("SELECT COUNT(*) > 0 FROM bookmarkRepo WHERE id=:id AND name=:name")
    suspend fun isBookMark(id: Int, name: String): Boolean
}
