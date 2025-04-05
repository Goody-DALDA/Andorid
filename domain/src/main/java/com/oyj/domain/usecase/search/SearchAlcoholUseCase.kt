package com.oyj.domain.usecase.search

import com.oyj.domain.Alcohol
import com.oyj.domain.repository.DataAlcoholRepository
import javax.inject.Inject

class SearchAlcoholUseCase @Inject constructor(
    private val repository: DataAlcoholRepository
) {
    suspend operator fun invoke(query: String): List<Alcohol> {
        return repository.getSearchedAlcoholData(query = query)
    }
}