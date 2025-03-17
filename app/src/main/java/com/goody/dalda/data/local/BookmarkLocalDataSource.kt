package com.goody.dalda.data.local

import com.goody.dalda.data.AlcoholData

interface BookmarkLocalDataSource {
    suspend fun insertAlcohol(alcoholData: AlcoholData)

    suspend fun deleteAlcohol(alcoholData: AlcoholData)

    suspend fun getBookmarkAlcoholList(): List<AlcoholData>

    suspend fun isBookMark(alcoholData: AlcoholData): Boolean
}
