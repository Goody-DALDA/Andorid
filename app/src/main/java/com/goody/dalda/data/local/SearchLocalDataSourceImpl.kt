package com.goody.dalda.data.local

import com.goody.dalda.data.database.dao.SearchDao
import com.goody.dalda.data.database.entity.SearchEntity
import javax.inject.Inject

class SearchLocalDataSourceImpl @Inject constructor(private val searchDao: SearchDao) :
    SearchLocalDataSource {
    override fun insertSearchWord(searchWord: String) {

        checkSearchWord(searchWord)

        val searchEntity = SearchEntity(
            searchWord = searchWord
        )

        searchDao.insertSearchWord(searchEntity)
    }

    override fun deleteSearchWord(searchWord: String) {
        searchDao.deleteSearchWord(searchWord)
    }

    override fun deleteAllSearchWord() {
        searchDao.deleteAllSearchWord()
    }

    override fun getSearchWordList(isDesc: Boolean): List<String> {
        return searchDao.getAllSearchWord(
            isDesc = isDesc
        ).map {
            it.searchWord
        }
    }

    private fun checkSearchWord(searchWord: String) {
        if (searchDao.isSearchWord(searchWord)) {
            deleteSearchWord(searchWord)
        }
    }
}