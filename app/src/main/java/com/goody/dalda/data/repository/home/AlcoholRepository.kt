package com.goody.dalda.data.repository.home

import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.repository.SearchAlcoholData

interface AlcoholRepository {
    suspend fun getAlcoholInfo(category: String): List<AlcoholData>

    suspend fun getSearchedAlcoholInfo(query: String): SearchAlcoholData

    suspend fun getRecommendAlcoholList(query: String): List<String>
}
