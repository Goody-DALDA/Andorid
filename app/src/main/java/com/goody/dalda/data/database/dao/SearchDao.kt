package com.goody.dalda.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.goody.dalda.data.database.entity.SearchEntity

@Dao
interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSearchWord(searchEntity: SearchEntity)

    @Query("Delete FROM searchWordRepo WHERE searchWord=:searchWord")
    fun deleteSearchWord(searchWord: String)

    @Query("Delete FROM searchWordRepo")
    fun deleteAllSearchWord()

    @Query("SELECT * FROM searchWordRepo ORDER BY CASE WHEN :isDesc = 1 THEN id END DESC LIMIT 5")
    fun getAllSearchWord(isDesc: Boolean = false): List<SearchEntity>

    @Query("SELECT EXISTS( SELECT * FROM searchWordRepo WHERE searchWord=:searchWord)")
    fun isSearchWord(searchWord: String): Boolean
}
