package com.goody.dalda.data.repository.home

import com.goody.dalda.data.AlcoholData

interface AlcoholRepository {
    suspend fun getAlcoholInfo(category: String): List<AlcoholData>
}
