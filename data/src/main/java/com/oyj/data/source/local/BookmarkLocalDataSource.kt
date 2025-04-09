package com.oyj.data.source.local

import com.oyj.domain.model.AlcoholEntity
import kotlinx.coroutines.flow.Flow


interface BookmarkLocalDataSource {
    suspend fun insertAlcohol(alcoholEntityData: AlcoholEntity)

    suspend fun deleteAlcohol(alcoholEntityData: AlcoholEntity)

    suspend fun getBookmarkAlcoholList(): Flow<List<AlcoholEntity>>

    fun isBookMark(alcoholEntityData: AlcoholEntity): Flow<Boolean>
}
