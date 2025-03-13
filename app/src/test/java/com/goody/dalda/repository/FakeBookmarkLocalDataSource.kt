package com.goody.dalda.repository

import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.local.BookmarkLocalDataSource

class FakeBookmarkLocalDataSource: BookmarkLocalDataSource {
    override fun insertAlcohol(alcoholData: AlcoholData) {
        TODO("Not yet implemented")
    }

    override fun deleteAlcohol(alcoholData: AlcoholData) {
        TODO("Not yet implemented")
    }

    override fun getBookmarkAlcoholList(): List<AlcoholData> {
        TODO("Not yet implemented")
    }

    override fun isBookMark(alcoholData: AlcoholData): Boolean {
        TODO("Not yet implemented")
    }
}