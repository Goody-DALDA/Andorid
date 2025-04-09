package com.oyj.data.repository

import android.util.Log
import com.oyj.data.dto.home.AlcoholDataDto
import com.oyj.data.mapper.DtoToDomainMapper.dataToAlcohol
import com.oyj.data.mapper.DtoToDomainMapper.searchResultDtoToSearchAlcoholData
import com.oyj.data.source.local.BookmarkLocalDataSource
import com.oyj.data.source.remote.home.AlcoholDataRemoteDataSource
import com.oyj.domain.repository.DataAlcoholRepository
import com.oyj.domain.model.AlcoholEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DataAlcoholRepositoryImpl @Inject constructor(
    private val alcoholDataRemoteDataSource: AlcoholDataRemoteDataSource,
    private val bookmarkLocalDataSource: BookmarkLocalDataSource,
) : DataAlcoholRepository {
    override suspend fun getAlcoholData(category: String): Flow<List<AlcoholEntity>> = flow {
        val response = alcoholDataRemoteDataSource.getAlcoholData(category = category,)

        if (response.isSuccessful) {
            val alcoholDataDto = requireNotNull(response.body()) { "Response body is null" }
            emit(alcoholDataDtoToAlcoholData(alcoholDataDto))
        } else {
            Log.e(TAG, "getAlcoholData: Failed to get alcohol info")
            emit(emptyList())
        }
    }.catch { e ->
        Log.e(TAG, "getAlcoholData: ${e.message}")
        emit(emptyList())
    }


    override suspend fun getSearchedAlcoholData(query: String): Flow<List<AlcoholEntity>> {
        return flow {
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
            emit(emptyList())
        }
    }.catch { e ->
        Log.e(TAG, "getRecommendAlcoholList: ${e.message}")
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
        alcoholDataDto: AlcoholDataDto
    ): List<AlcoholEntity> {
        return dataToAlcohol(alcoholDataDto.alcoholDataList)
    }

    companion object {
        private const val TAG = "DataAlcoholRepositoryImpl"
    }
}