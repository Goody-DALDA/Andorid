package com.oyj.dataa.repository

import com.oyj.dataa.source.local.SearchLocalDataSource
import com.oyj.domain.repository.DataSearchRepository
import javax.inject.Inject

class DataSearchRepositoryImpl @Inject constructor(
    private val searchLocalDataSource: SearchLocalDataSource,
) : DataSearchRepository {
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
            isDesc = isDesc,
        )
    }

    companion object {
        private const val TAG = "SearchRepositoryImpl"
    }
}