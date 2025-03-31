package com.oyj.domain.usecase

import com.oyj.domain.repository.DataSearchRepository
import javax.inject.Inject

class DeleteAllSearchWordUseCase @Inject constructor(private val repository: DataSearchRepository) {
    suspend operator fun invoke() {
        repository.deleteAllSearchWord()
    }
}