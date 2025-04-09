package com.oyj.domain.repository

import com.oyj.domain.model.AlcoholEntity
import kotlinx.coroutines.flow.Flow

interface AlcoholRepository {
    suspend fun getAlcoholData(category: String): Flow<List<AlcoholEntity>>

    suspend fun getSearchedAlcoholData(query: String): Flow<List<AlcoholEntity>>

    suspend fun getRecommendAlcoholList(query: String): Flow<List<String>>

    suspend fun getBookmarkAlcoholList(): Flow<List<AlcoholEntity>>

    suspend fun insertBookmarkAlcohol(alcoholEntity: AlcoholEntity)

    suspend fun deleteBookmarkAlcohol(alcoholEntity: AlcoholEntity)

    suspend fun isBookmarkAlcohol(alcoholEntity: AlcoholEntity): Boolean
}