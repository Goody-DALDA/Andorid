package com.goody.dalda.data.repository.search

interface SearchRepository {
    fun insertSearchWord(searchWord: String)
    fun deleteSearchWord(searchWord: String)
    fun deleteAllSearchWord()
    fun getSearchWordList(isDesc: Boolean): List<String>
}