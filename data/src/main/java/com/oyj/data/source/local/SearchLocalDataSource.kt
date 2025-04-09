package com.oyj.data.source.local

import kotlinx.coroutines.flow.Flow

interface SearchLocalDataSource {
    fun insertSearchWord(searchWord: String)

    fun deleteSearchWord(searchWord: String)

    fun deleteAllSearchWord()

    fun getSearchWordList(isDesc: Boolean): Flow<List<String>>
}
