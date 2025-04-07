package com.oyj.domain.repository

import com.oyj.domain.model.AlcoholEntity

interface AlcoholRepository {
    suspend fun getAlcoholData(category: String): List<AlcoholEntity>

    suspend fun getSearchedAlcoholData(query: String): List<AlcoholEntity>

    suspend fun getRecommendAlcoholList(query: String): List<String>

    suspend fun getBookmarkAlcoholList(): List<AlcoholEntity>

    suspend fun insertBookmarkAlcohol(alcoholEntity: AlcoholEntity)

    suspend fun deleteBookmarkAlcohol(alcoholEntity: AlcoholEntity)

    suspend fun isBookmarkAlcohol(alcoholEntity: AlcoholEntity): Boolean
}