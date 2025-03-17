package com.goody.dalda.repository

import com.goody.dalda.data.AlcoholData
import com.goody.dalda.data.local.BookmarkLocalDataSource

class FakeBookmarkLocalDataSourceImpl : BookmarkLocalDataSource {
    override suspend fun insertAlcohol(alcoholData: AlcoholData) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAlcohol(alcoholData: AlcoholData) {
        TODO("Not yet implemented")
    }

    override suspend fun getBookmarkAlcoholList(): List<AlcoholData> {
        TODO("Not yet implemented")
    }

    override suspend fun isBookMark(alcoholData: AlcoholData): Boolean {
        TODO("Not yet implemented")
    }
}
