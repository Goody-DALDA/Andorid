package com.oyj.dataa.source.local

import com.oyj.domain.Alcohol


interface BookmarkLocalDataSource {
    suspend fun insertAlcohol(alcoholData: Alcohol)

    suspend fun deleteAlcohol(alcoholData: Alcohol)

    suspend fun getBookmarkAlcoholList(): List<Alcohol>

    suspend fun isBookMark(alcoholData: Alcohol): Boolean
}
