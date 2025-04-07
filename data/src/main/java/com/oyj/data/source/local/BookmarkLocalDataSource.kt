package com.oyj.data.source.local

import com.oyj.domain.model.AlcoholEntity


interface BookmarkLocalDataSource {
    suspend fun insertAlcohol(alcoholEntityData: AlcoholEntity)

    suspend fun deleteAlcohol(alcoholEntityData: AlcoholEntity)

    suspend fun getBookmarkAlcoholList(): List<AlcoholEntity>

    suspend fun isBookMark(alcoholEntityData: AlcoholEntity): Boolean
}
