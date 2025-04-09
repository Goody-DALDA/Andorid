package com.oyj.data.repository

import android.util.Log
import com.oyj.data.dto.toDomain
import com.oyj.data.source.remote.NoticeRemoteDataSource
import com.oyj.domain.model.PostEntity
import com.oyj.domain.repository.NoticeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(
    private val dataSource: NoticeRemoteDataSource
) : NoticeRepository {
    override suspend fun fetchNotice(): Flow<List<PostEntity>> = flow {
        runCatching {
            val response = dataSource.fetchNotice()
            if (response.isSuccessful) {
                val result = response.body()?.data?.map { it.toDomain() }
                emit(result ?: emptyList())
            } else {
                Log.e(TAG, "fetchNotice: ${response.message()}")
                emit(emptyList())
            }
        }.onFailure { throwable ->
            Log.e(TAG, "Failed to get blog data", throwable)
            emit(emptyList())
        }
    }

    companion object {
        private const val TAG = "NoticeRepositoryImpl"
    }
}