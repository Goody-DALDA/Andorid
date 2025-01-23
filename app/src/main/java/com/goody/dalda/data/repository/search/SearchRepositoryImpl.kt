package com.goody.dalda.data.repository.search

import com.goody.dalda.data.local.SearchLocalDataSource
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchLocalDataSource: SearchLocalDataSource
) : SearchRepository {
    override fun insertSearchWord(searchWord: String) {
        searchLocalDataSource.insertSearchWord(searchWord)
    }

    override fun deleteSearchWord(searchWord: String) {
        searchLocalDataSource.deleteSearchWord(searchWord)
    }

    override fun deleteAllSearchWord() {
        searchLocalDataSource.deleteAllSearchWord()
    }

    override fun getSearchWordList(isDesc: Boolean): List<String> {
        return searchLocalDataSource.getSearchWordList(
            isDesc = isDesc
        )
    }
}