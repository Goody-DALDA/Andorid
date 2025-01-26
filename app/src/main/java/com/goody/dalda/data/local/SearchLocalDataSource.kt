package com.goody.dalda.data.local

interface SearchLocalDataSource {
    fun insertSearchWord(searchWord: String)

    fun deleteSearchWord(searchWord: String)

    fun deleteAllSearchWord()

    fun getSearchWordList(isDesc: Boolean): List<String>
}
