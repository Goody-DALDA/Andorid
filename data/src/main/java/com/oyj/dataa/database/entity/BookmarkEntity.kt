package com.oyj.dataa.database.entity

import androidx.room.Entity

@Entity(
    primaryKeys = ["id", "name"],
    tableName = "bookmarkRepo",
)
data class BookmarkEntity(
    // 공통 요소
    val id: Int,
    val name: String,
    val imgUrl: String,
    val country: String,
    val volume: String,
    val abv: String,
    val category: String,
    // 주류별 개별 요소
    val appearance: Float? = null,
    val flavor: Float? = null,
    val mouthfeel: Float? = null,
    val aromaFlot: Float? = null,
    val aromaStr: String? = null,
    val type: String? = null,
    val price: Int? = null,
    val taste: String? = null,
    val finish: String? = null,
    val comment: String? = null,
    val ingredient: String? = null,
    val pairingFood: String? = null,
    val brewery: String? = null,
    val sugar: Float? = null,
    val acid: Float? = null,
    val winery: String? = null,
)
