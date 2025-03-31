package com.oyj.dataa.repository

import com.oyj.domain.repository.DataSearchRepository
import javax.inject.Inject

class DataSearchRepositoryImpl @Inject constructor() : DataSearchRepository {
    override fun insertSearchWord(searchWord: String) {
        println("$TAG - insertSearchWord: searchWord = $searchWord")
    }

    override fun deleteSearchWord(searchWord: String) {
        println("$TAG - deleteSearchWord: searchWord = $searchWord")
    }

    override fun deleteAllSearchWord() {
        println("$TAG - deleteAllSearchWord: ")
    }

    override fun getSearchWordList(isDesc: Boolean): List<String> {
        println("$TAG - getSearchWordList: isDesc = $isDesc")
        return emptyList()
    }

    companion object {
        private const val TAG = "SearchRepositoryImpl"
    }
}