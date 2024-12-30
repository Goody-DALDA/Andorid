package com.goody.dalda.ui.home

import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.remote.home.AlcoholInfoRemoteDataSource
import com.goody.dalda.data.repository.SearchAlcoholData
import com.goody.dalda.data.repository.home.AlcoholRepository

class FakeAlcoholRepositoryImpl (
    private val alcoholInfoRemoteDataSource: AlcoholInfoRemoteDataSource
) : AlcoholRepository {
    override suspend fun getAlcoholInfo(category: String): List<AlcoholData> {
        return emptyList()
    }

    override suspend fun getSearchedAlcoholInfo(query: String): SearchAlcoholData {
        return SearchAlcoholData()
    }

    override suspend fun getRecommendAlcoholList(query: String): List<String> {
        TODO("Not yet implemented")
    }
}

