package com.oyj.dataa.source.local

interface SearchLocalDataSource {
    fun insertSearchWord(searchWord: String)

    fun deleteSearchWord(searchWord: String)

    fun deleteAllSearchWord()

    fun getSearchWordList(isDesc: Boolean): List<String>
}
