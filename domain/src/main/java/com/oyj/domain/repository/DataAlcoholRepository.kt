package com.oyj.domain.repository

import com.oyj.domain.model.AlcoholEntity
import kotlinx.coroutines.flow.Flow

interface DataAlcoholRepository {
    suspend fun getAlcoholData(category: String): Flow<List<AlcoholEntity>>

    // 주류 검색 데이터
    suspend fun getSearchedAlcoholData(query: String): Flow<List<AlcoholEntity>>

    // 추천 주류 검색어
    suspend fun getRecommendAlcoholList(query: String): Flow<List<String>>

    suspend fun getBookmarkAlcoholList(): Flow<List<AlcoholEntity>>

    suspend fun insertBookmarkAlcohol(alcoholEntity: AlcoholEntity)

    suspend fun deleteBookmarkAlcohol(alcoholEntity: AlcoholEntity)

    suspend fun isBookmarkAlcohol(alcoholEntity: AlcoholEntity): Boolean
}