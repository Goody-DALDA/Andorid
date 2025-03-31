package com.oyj.domain.usecase

import com.oyj.domain.repository.DataSearchRepository
import javax.inject.Inject

// 최근 검색어
class GetRecentSearchWordListUseCase @Inject constructor(private val repository: DataSearchRepository) {
    suspend operator fun invoke(isDesc: Boolean): List<String> {
        return repository.getSearchWordList(isDesc = isDesc)
    }
}