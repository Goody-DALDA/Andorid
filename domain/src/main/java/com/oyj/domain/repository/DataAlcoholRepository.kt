package com.oyj.domain.repository

import com.oyj.domain.Alcohol

interface DataAlcoholRepository {
    suspend fun getAlcoholData(category: String): List<Alcohol>

    // 주류 검색 데이터
    suspend fun getSearchedAlcoholData(query: String): List<Alcohol>

    // 추천 주류 검색어
    suspend fun getRecommendAlcoholList(query: String): List<String>

    suspend fun getBookmarkAlcoholList(): List<Alcohol>

    suspend fun insertBookmarkAlcohol(alcohol: Alcohol)

    suspend fun deleteBookmarkAlcohol(alcohol: Alcohol)

    suspend fun isBookmarkAlcohol(alcohol: Alcohol): Boolean
}