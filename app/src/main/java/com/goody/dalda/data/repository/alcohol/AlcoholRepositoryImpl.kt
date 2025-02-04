package com.goody.dalda.data.repository.alcohol

import android.util.Log
import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.dto.home.AlcoholDataDto
import com.goody.dalda.data.dto.home.Beer
import com.goody.dalda.data.dto.home.Sake
import com.goody.dalda.data.dto.home.Soju
import com.goody.dalda.data.dto.home.TraditionalLiquor
import com.goody.dalda.data.dto.home.Whisky
import com.goody.dalda.data.dto.home.Wine
import com.goody.dalda.data.dto.search.SearchData
import com.goody.dalda.data.local.BookmarkLocalDataSource
import com.goody.dalda.data.mapper.AlcoholDataMapper.dataToAlcoholData
import com.goody.dalda.data.remote.home.AlcoholDataRemoteDataSource
import com.goody.dalda.data.repository.SearchAlcoholData
import javax.inject.Inject

class AlcoholRepositoryImpl
    @Inject
    constructor(
        private val alcoholDataRemoteDataSource: AlcoholDataRemoteDataSource,
        private val bookmarkLocalDataSource: BookmarkLocalDataSource,
    ) : AlcoholRepository {
        override suspend fun getAlcoholData(category: String): List<AlcoholData> =
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

        override suspend fun getSearchedAlcoholData(query: String): SearchAlcoholData {
            return try {
                val response = alcoholDataRemoteDataSource.getSearchedAlcoholData(query = query)

                if (response.isSuccessful) {
                    val searchResultDto = requireNotNull(response.body()) { "Response body is null" }
                    searchResultDtoToSearchAlcoholData(searchResultDto.searchData)
                } else {
                    Log.e(TAG, "getSearchedAlcoholData: Failed to get searched alcohol info")
                    return SearchAlcoholData()
                }
            } catch (e: Exception) {
                Log.e(TAG, "getSearchedAlcoholData: ${e.message}")
                return SearchAlcoholData()
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

        override suspend fun getBookmarkAlcoholList(): List<AlcoholData> {
            val bookmarkAlcoholList = bookmarkLocalDataSource.getBookmarkAlcoholList()

            return bookmarkAlcoholList
        }

        override suspend fun insertBookmarkAlcohol(alcoholData: AlcoholData) {
            bookmarkLocalDataSource.insertAlcohol(alcoholData)
        }

        override suspend fun deleteBookmarkAlcohol(alcoholData: AlcoholData) {
            bookmarkLocalDataSource.deleteAlcohol(alcoholData)
        }

        override suspend fun isBookmarkAlcohol(alcoholData: AlcoholData): Boolean = bookmarkLocalDataSource.isBookMark(alcoholData)

        private fun alcoholDataDtoToAlcoholData(
            category: String,
            alcoholDataDto: AlcoholDataDto,
        ): List<AlcoholData> {
            when (category.lowercase()) {
                "soju" -> {
                    return dataToAlcoholData(alcoholDataDto.alcoholDataList.filterIsInstance<Soju>())
                }

                "whisky" -> {
                    return dataToAlcoholData(alcoholDataDto.alcoholDataList.filterIsInstance<Whisky>())
                }

                "beer" -> {
                    return dataToAlcoholData(alcoholDataDto.alcoholDataList.filterIsInstance<Beer>())
                }

                "wine" -> {
                    return dataToAlcoholData(alcoholDataDto.alcoholDataList.filterIsInstance<Wine>())
                }

                "traditionalliquor" -> {
                    return dataToAlcoholData(alcoholDataDto.alcoholDataList.filterIsInstance<TraditionalLiquor>())
                }

                "sake" -> {
                    return dataToAlcoholData(alcoholDataDto.alcoholDataList.filterIsInstance<Sake>())
                }

                else -> {
                    throw Exception("empty category")
                }
            }
        }

        private fun searchResultDtoToSearchAlcoholData(searchResultDto: SearchData): SearchAlcoholData =
            SearchAlcoholData(
                sojuList = dataToAlcoholData(searchResultDto.soju).map { it as AlcoholData.Soju },
                beerList = dataToAlcoholData(searchResultDto.beer).map { it as AlcoholData.Beer },
                sakeList = dataToAlcoholData(searchResultDto.sake).map { it as AlcoholData.Sake },
                wineList = dataToAlcoholData(searchResultDto.wine).map { it as AlcoholData.Wine },
                whiskyList = dataToAlcoholData(searchResultDto.whisky).map { it as AlcoholData.Whisky },
                traditionalLiquorList = dataToAlcoholData(searchResultDto.traditionalLiquor).map { it as AlcoholData.TraditionalLiquor },
            )

        companion object {
            private const val TAG = "AlcoholRepositoryImpl"
        }
    }
