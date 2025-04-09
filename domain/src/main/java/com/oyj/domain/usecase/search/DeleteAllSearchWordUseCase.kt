package com.oyj.domain.usecase.search

import com.oyj.domain.repository.DataSearchRepository
import javax.inject.Inject

class DeleteAllSearchWordUseCase @Inject constructor(
    private val repository: DataSearchRepository
) {
    operator fun invoke() {
        repository.deleteAllSearchWord()
    }
}