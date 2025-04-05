package com.oyj.domain.usecase.search

import com.oyj.domain.Alcohol
import com.oyj.domain.repository.DataAlcoholRepository
import javax.inject.Inject

class GetAlcoholDataUseCase @Inject constructor(
    private val repository: DataAlcoholRepository
) {
    suspend operator fun invoke(category: String): List<Alcohol> {
        return repository.getAlcoholData(category = category)
    }
}