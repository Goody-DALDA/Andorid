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
    fun insertAlcohol(bookmarkEntity: BookmarkEntity)

    @Delete
    fun deleteAlcohol(bookmarkEntity: BookmarkEntity)

    @Query("SELECT * FROM bookmarkRepo")
    fun getAllBookMark(): List<BookmarkEntity>

    @Query("SELECT * FROM bookmarkRepo WHERE id=:id AND name=:name")
    fun isBookMark(
        id: Int,
        name: String,
    ): Boolean
}
