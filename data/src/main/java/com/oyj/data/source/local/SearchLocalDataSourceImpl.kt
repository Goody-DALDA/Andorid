package com.oyj.data.source.local

import com.oyj.data.database.dao.SearchDao
import com.oyj.data.database.entity.SearchEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchLocalDataSourceImpl @Inject constructor(
    private val searchDao: SearchDao
) : SearchLocalDataSource {
    override fun insertSearchWord(searchWord: String) {
        checkSearchWord(searchWord)

        val searchEntity = SearchEntity(
                searchWord = searchWord,
            )

        searchDao.insertSearchWord(searchEntity)
    }

    override fun deleteSearchWord(searchWord: String) {
        searchDao.deleteSearchWord(searchWord)
    }

    override fun deleteAllSearchWord() {
        searchDao.deleteAllSearchWord()
    }

    override fun getSearchWordList(isDesc: Boolean): Flow<List<String>> {
        return searchDao.getAllSearchWord(
            isDesc = isDesc,
        ).map { searchEntities ->
            searchEntities.map(SearchEntity::searchWord)
        }
    }

    private fun checkSearchWord(searchWord: String) {
        if (searchDao.isSearchWord(searchWord)) {
            deleteSearchWord(searchWord)
        }
    }
}
