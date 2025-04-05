package com.oyj.domain.usecase.search

import com.oyj.domain.repository.DataSearchRepository
import javax.inject.Inject

class InsertSearchWordUseCase @Inject constructor(
    private val repository: DataSearchRepository
) {
    suspend operator fun invoke(searchWord: String) {
        repository.insertSearchWord(searchWord = searchWord)
    }
}