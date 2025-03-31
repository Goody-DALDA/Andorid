package com.oyj.dataa.repository

import com.oyj.domain.repository.DataAlcoholRepository
import com.oyj.domain.Alcohol
import javax.inject.Inject

class DataAlcoholRepositoryImpl @Inject constructor(): DataAlcoholRepository {
    override suspend fun getAlcoholData(category: String): List<Alcohol> {
        println("$TAG - getAlcoholData : category=$category")

        return emptyList()
    }

    override suspend fun getSearchedAlcoholData(query: String): List<Alcohol> {
        println("$TAG - getSearchedAlcoholData : query=$query")
        return emptyList()
    }

    override suspend fun getRecommendAlcoholList(query: String): List<String> {
        println("$TAG - getRecommendAlcoholList: query=$query")
        return emptyList()
    }

    override suspend fun getBookmarkAlcoholList(): List<Alcohol> {
        println("$TAG - getBookmarkAlcoholList: ")
        return emptyList()
    }

    override suspend fun insertBookmarkAlcohol(alcohol: Alcohol) {
        println("$TAG - insertBookmarkAlcohol: alcohol=$alcohol")
    }

    override suspend fun deleteBookmarkAlcohol(alcohol: Alcohol) {
        println("$TAG - deleteBookmarkAlcohol: alcohol=$alcohol")
    }

    override suspend fun isBookmarkAlcohol(alcohol: Alcohol): Boolean {
        println("$TAG - isBookmarkAlcohol: alcohol=$alcohol")
        return false
    }

    companion object {
        private const val TAG = "DataAlcoholRepositoryIm"
    }
}