package com.oyj.domain.usecase.search

import com.oyj.domain.repository.DataSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// 최근 검색어
class GetRecentSearchWordListUseCase @Inject constructor(
    private val repository: DataSearchRepository
) {
    operator fun invoke(isDesc: Boolean): Flow<List<String>> {
        return repository.getSearchWordList(isDesc = isDesc)
    }
}