package com.oyj.domain

import javax.inject.Inject

class SearchAlcoholUseCase @Inject constructor(private val repository: AlcoholRepository) {
    suspend fun invoke(query: String): List<Alcohol> {
        return repository.getSearchedAlcoholData(
            query = query
        )
    }
}