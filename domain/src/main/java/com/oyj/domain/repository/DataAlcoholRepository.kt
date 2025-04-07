package com.oyj.domain.repository

import com.oyj.domain.model.AlcoholEntity

interface DataAlcoholRepository {
    suspend fun getAlcoholData(category: String): List<AlcoholEntity>

    // 주류 검색 데이터
    suspend fun getSearchedAlcoholData(query: String): List<AlcoholEntity>

    // 추천 주류 검색어
    suspend fun getRecommendAlcoholList(query: String): List<String>

    suspend fun getBookmarkAlcoholList(): List<AlcoholEntity>

    suspend fun insertBookmarkAlcohol(alcoholEntity: AlcoholEntity)

    suspend fun deleteBookmarkAlcohol(alcoholEntity: AlcoholEntity)

    suspend fun isBookmarkAlcohol(alcoholEntity: AlcoholEntity): Boolean
}