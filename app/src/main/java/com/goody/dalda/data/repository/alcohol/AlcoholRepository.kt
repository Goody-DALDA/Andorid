package com.goody.dalda.data.repository.alcohol

import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.repository.SearchAlcoholData

interface AlcoholRepository {
    suspend fun getAlcoholData(category: String): List<AlcoholData>

    suspend fun getSearchedAlcoholData(query: String): SearchAlcoholData

    suspend fun getRecommendAlcoholList(query: String): List<String>

    suspend fun getBookmarkAlcoholList(): List<AlcoholData>

    suspend fun insertBookmarkAlcohol(alcoholData: AlcoholData)

    suspend fun deleteBookmarkAlcohol(alcoholData: AlcoholData)

    suspend fun isBookmarkAlcohol(alcoholData: AlcoholData): Boolean
}
