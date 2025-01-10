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
        return emptyList()
    }

    override suspend fun getBookmarkAlcoholList(): List<AlcoholData> {
        TODO("Not yet implemented")
    }

    override suspend fun insertBookmarkAlcohol(alcoholData: AlcoholData) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteBookmarkAlcohol(alcoholData: AlcoholData) {
        TODO("Not yet implemented")
    }

    override suspend fun isBookmarkAlcohol(alcoholData: AlcoholData): Boolean {
        TODO("Not yet implemented")
    }
}

