package com.goody.dalda.data.local

import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.database.entity.BookmarkEntity

interface BookmarkLocalDataSource {
    fun insertAlcohol(alcoholData: AlcoholData)
    fun deleteAlcohol(alcoholData: AlcoholData)
    fun getBookmarkAlcoholList(): List<AlcoholData>
    fun isBookMark(alcoholData: AlcoholData): Boolean
}