package com.goody.dalda.data.local

import com.goody.dalda.data.AlcoholData

interface BookmarkLocalDataSource {
    fun insertAlcohol(alcoholData: AlcoholData)

    fun deleteAlcohol(alcoholData: AlcoholData)

    fun getBookmarkAlcoholList(): List<AlcoholData>

    fun isBookMark(alcoholData: AlcoholData): Boolean
}
