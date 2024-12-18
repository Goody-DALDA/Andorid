package com.goody.dalda.ui.home

import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.remote.home.AlcoholInfoRemoteDataSource
import com.goody.dalda.data.repository.home.AlcoholRepository

class FakeAlcoholRepositoryImpl (
    private val alcoholInfoRemoteDataSource: AlcoholInfoRemoteDataSource
) : AlcoholRepository {
    override suspend fun getAlcoholInfo(category: String): List<AlcoholData> {
        return emptyList()
    }
}

