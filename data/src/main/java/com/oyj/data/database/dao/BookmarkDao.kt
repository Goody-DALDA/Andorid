package com.oyj.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oyj.data.database.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAlcohol(bookmarkEntity: BookmarkEntity)

    @Delete
    suspend fun deleteAlcohol(bookmarkEntity: BookmarkEntity)

    @Query("SELECT * FROM bookmarkRepo")
    suspend fun getAllBookMark(): List<BookmarkEntity>

    @Query("SELECT COUNT(*) > 0 FROM bookmarkRepo WHERE id=:id AND name=:name")
    fun isBookMark(id: Int, name: String): Flow<Boolean>
}
