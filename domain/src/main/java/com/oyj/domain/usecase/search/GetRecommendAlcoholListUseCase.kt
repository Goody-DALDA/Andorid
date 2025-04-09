package com.oyj.domain.usecase.search

import com.oyj.domain.repository.DataAlcoholRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecommendAlcoholListUseCase @Inject constructor(
    private val repository: DataAlcoholRepository
) {
    suspend operator fun invoke(query: String): Flow<List<String>> {
        return repository.getRecommendAlcoholList(query = query)
    }
}