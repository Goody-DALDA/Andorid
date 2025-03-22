package com.oyj.domain

interface AlcoholRepository {
    suspend fun getAlcoholData(category: String): List<Alcohol>

    suspend fun getSearchedAlcoholData(query: String): List<Alcohol>

    suspend fun getRecommendAlcoholList(query: String): List<String>

    suspend fun getBookmarkAlcoholList(): List<Alcohol>

    suspend fun insertBookmarkAlcohol(alcohol: Alcohol)

    suspend fun deleteBookmarkAlcohol(alcohol: Alcohol)

    suspend fun isBookmarkAlcohol(alcohol: Alcohol): Boolean
}