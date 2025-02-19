package com.goody.dalda.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "searchWordRepo",
)
data class SearchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val searchWord: String,
)
