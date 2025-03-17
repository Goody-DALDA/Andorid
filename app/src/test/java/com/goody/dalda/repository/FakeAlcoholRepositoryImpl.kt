package com.goody.dalda.repository

import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.local.BookmarkLocalDataSource
import com.goody.dalda.data.remote.home.AlcoholDataRemoteDataSource
import com.goody.dalda.data.repository.SearchAlcoholData
import com.goody.dalda.data.repository.alcohol.AlcoholRepository

class FakeAlcoholRepositoryImpl(
    private val alcoholDataRemoteDataSource: AlcoholDataRemoteDataSource,
    private val bookmarkLocalDataSource: BookmarkLocalDataSource,
) : AlcoholRepository {
    override suspend fun getAlcoholData(category: String): List<AlcoholData> = emptyList()

    override suspend fun getSearchedAlcoholData(query: String): SearchAlcoholData = SearchAlcoholData()

    override suspend fun getRecommendAlcoholList(query: String): List<String> = emptyList()

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
