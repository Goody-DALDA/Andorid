package com.oyj.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataSearchRepository {
    fun insertSearchWord(searchWord: String)

    // 최근 검색어 DB 삭제
    fun deleteSearchWord(searchWord: String)

    // 모든 최근 검색어 삭제
    fun deleteAllSearchWord()

    // 최근 검색어 호출
    fun getSearchWordList(isDesc: Boolean): Flow<List<String>>
}