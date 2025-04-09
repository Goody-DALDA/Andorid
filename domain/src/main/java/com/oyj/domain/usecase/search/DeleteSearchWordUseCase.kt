package com.oyj.domain.usecase.search

import com.oyj.domain.repository.DataSearchRepository
import javax.inject.Inject

class DeleteSearchWordUseCase @Inject constructor(
    private val repository: DataSearchRepository
) {
    operator fun invoke(searchWord: String) {
        repository.deleteSearchWord(searchWord = searchWord)
    }
}