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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AlcoholRepositoryImpl @Inject constructor(
    private val alcoholDataRemoteDataSource: AlcoholDataRemoteDataSource,
    private val bookmarkLocalDataSource: BookmarkLocalDataSource,
) : AlcoholRepository {
    override suspend fun getAlcoholData(category: String): Flow<List<AlcoholEntity>> =
        flow {
            val response = alcoholDataRemoteDataSource.getAlcoholData(
                category = category,
            )
            if (response.isSuccessful) {
                val alcoholDataDto = requireNotNull(response.body()) { "Response body is null" }
                emit(alcoholDataDtoToAlcoholData(category, alcoholDataDto))
            } else {
                Log.e(TAG, "getAlcoholData: Failed to get alcohol info")
                emit(emptyList())
            }
        }.catch { e ->
            Log.e(TAG, "getAlcoholData: ${e.message}")
            emit(emptyList())
        }

    override suspend fun getSearchedAlcoholData(query: String): Flow<List<AlcoholEntity>> = flow {
        val response = alcoholDataRemoteDataSource.getSearchedAlcoholData(query = query)
        if (response.isSuccessful) {
            val searchResultDto = requireNotNull(response.body()) { "Response body is null" }
            emit(searchResultDtoToSearchAlcoholData(searchResultDto.searchData))
        } else {
            Log.e(TAG, "getSearchedAlcoholData: Failed to get searched alcohol info")
            emit(emptyList())
        }
    }.catch { e ->
        Log.e(TAG, "getSearchedAlcoholData: ${e.message}")
        emit(emptyList())
    }

    override suspend fun getRecommendAlcoholList(query: String): Flow<List<String>> = flow {
        val response = alcoholDataRemoteDataSource.getRecommendAlcoholList(query)
        if (response.isSuccessful) {
            val searchResultDto = requireNotNull(response.body()) { "Response body is null" }
            emit(
                searchResultDto.recommendAlcoholInfoList
                    .map {
                        it.name
                    }
            )
        } else {
            Log.e(TAG, "getRecommendAlcoholList: Failed to get recommend alcohol list")
            emit(emptyList())
        }
    }.catch { e ->
        Log.e(TAG, "getSearchedAlcoholData: ${e.message}")
        emit(emptyList())
    }

    override suspend fun getBookmarkAlcoholList(): Flow<List<AlcoholEntity>> {
        return bookmarkLocalDataSource.getBookmarkAlcoholList()
    }

    override suspend fun insertBookmarkAlcohol(alcoholEntity: AlcoholEntity) {
        bookmarkLocalDataSource.insertAlcohol(alcoholEntity)
    }

    override suspend fun deleteBookmarkAlcohol(alcoholEntity: AlcoholEntity) {
        bookmarkLocalDataSource.deleteAlcohol(alcoholEntity)
    }

    override fun isBookmarkAlcohol(alcoholEntity: AlcoholEntity): Flow<Boolean> =
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