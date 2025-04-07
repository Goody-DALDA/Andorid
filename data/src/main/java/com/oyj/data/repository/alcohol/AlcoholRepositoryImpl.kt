package com.oyj.data.repository.alcohol

import android.util.Log
import com.oyj.data.dto.home.AlcoholDataDto
import com.oyj.data.dto.home.Beer
import com.oyj.data.dto.home.Sake
import com.oyj.data.dto.home.Soju
import com.oyj.data.dto.home.TraditionalLiquor
import com.oyj.data.dto.home.Whisky
import com.oyj.data.dto.home.Wine
import com.oyj.data.mapper.DtoToDomainMapper.dataToAlcohol
import com.oyj.data.mapper.DtoToDomainMapper.searchResultDtoToSearchAlcoholData
import com.oyj.data.source.local.BookmarkLocalDataSource
import com.oyj.data.source.remote.home.AlcoholDataRemoteDataSource
import com.oyj.domain.model.AlcoholEntity
import com.oyj.domain.repository.AlcoholRepository
import javax.inject.Inject

class AlcoholRepositoryImpl @Inject constructor(
    private val alcoholDataRemoteDataSource: AlcoholDataRemoteDataSource,
    private val bookmarkLocalDataSource: BookmarkLocalDataSource,
) : AlcoholRepository {
    override suspend fun getAlcoholData(category: String): List<AlcoholEntity> =
        try {
            val response =
                alcoholDataRemoteDataSource.getAlcoholData(
                    category = category,
                )

            if (response.isSuccessful) {
                val alcoholDataDto = requireNotNull(response.body()) { "Response body is null" }
                alcoholDataDtoToAlcoholData(category, alcoholDataDto)
            } else {
                Log.e(TAG, "getAlcoholData: Failed to get alcohol info")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e(TAG, "getAlcoholData: ${e.message}")
            emptyList()
        }

    override suspend fun getSearchedAlcoholData(query: String): List<AlcoholEntity> {
        return try {
            val response = alcoholDataRemoteDataSource.getSearchedAlcoholData(query = query)

            if (response.isSuccessful) {
                val searchResultDto = requireNotNull(response.body()) { "Response body is null" }
                searchResultDtoToSearchAlcoholData(searchResultDto.searchData)
            } else {
                Log.e(TAG, "getSearchedAlcoholData: Failed to get searched alcohol info")
                return emptyList()
            }
        } catch (e: Exception) {
            Log.e(TAG, "getSearchedAlcoholData: ${e.message}")
            return emptyList()
        }
    }

    override suspend fun getRecommendAlcoholList(query: String): List<String> =
        try {
            val response = alcoholDataRemoteDataSource.getRecommendAlcoholList(query)

            if (response.isSuccessful) {
                val searchResultDto = requireNotNull(response.body()) { "Response body is null" }
                searchResultDto.recommendAlcoholInfoList.map {
                    it.name
                }
            } else {
                Log.e(TAG, "getRecommendAlcoholList: Failed to get recommend alcohol list")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e(TAG, "getRecommendAlcoholList: ${e.message}")
            emptyList()
        }

    override suspend fun getBookmarkAlcoholList(): List<AlcoholEntity> {
        val bookmarkAlcoholList = bookmarkLocalDataSource.getBookmarkAlcoholList()

        return bookmarkAlcoholList
    }

    override suspend fun insertBookmarkAlcohol(alcoholEntity: AlcoholEntity) {
        bookmarkLocalDataSource.insertAlcohol(alcoholEntity)
    }

    override suspend fun deleteBookmarkAlcohol(alcoholEntity: AlcoholEntity) {
        bookmarkLocalDataSource.deleteAlcohol(alcoholEntity)
    }

    override suspend fun isBookmarkAlcohol(alcoholEntity: AlcoholEntity): Boolean =
        bookmarkLocalDataSource.isBookMark(alcoholEntity)

    private fun alcoholDataDtoToAlcoholData(
        category: String,
        alcoholDataDto: AlcoholDataDto,
    ): List<AlcoholEntity> {
        when (category.lowercase()) {
            "soju" -> {
                return dataToAlcohol(alcoholDataDto.alcoholDataList.filterIsInstance<Soju>())
            }

            "whisky" -> {
                return dataToAlcohol(alcoholDataDto.alcoholDataList.filterIsInstance<Whisky>())
            }

            "beer" -> {
                return dataToAlcohol(alcoholDataDto.alcoholDataList.filterIsInstance<Beer>())
            }

            "wine" -> {
                return dataToAlcohol(alcoholDataDto.alcoholDataList.filterIsInstance<Wine>())
            }

            "traditionalliquor" -> {
                return dataToAlcohol(alcoholDataDto.alcoholDataList.filterIsInstance<TraditionalLiquor>())
            }

            "sake" -> {
                return dataToAlcohol(alcoholDataDto.alcoholDataList.filterIsInstance<Sake>())
            }

            else -> {
                throw Exception("empty category")
            }
        }
    }

    companion object {
        private const val TAG = "AlcoholRepositoryImpl"
    }
}