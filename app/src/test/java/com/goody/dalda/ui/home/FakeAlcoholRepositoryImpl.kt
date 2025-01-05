package com.goody.dalda.ui.home

import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.remote.home.AlcoholDataRemoteDataSource
import com.goody.dalda.data.repository.SearchAlcoholData
import com.goody.dalda.data.repository.home.AlcoholRepository

class FakeAlcoholRepositoryImpl (
    private val alcoholDataRemoteDataSource: AlcoholDataRemoteDataSource
) : AlcoholRepository {
    override suspend fun getAlcoholData(category: String): List<AlcoholData> {
        return emptyList()
    }

    override suspend fun getSearchedAlcoholData(query: String): SearchAlcoholData {
        return SearchAlcoholData()
    }

    override suspend fun getRecommendAlcoholList(query: String): List<String> {
        TODO("Not yet implemented")
    }
}

