package com.oyj.domain.usecase.search

import com.oyj.domain.model.AlcoholEntity
import com.oyj.domain.repository.DataAlcoholRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAlcoholDataUseCase @Inject constructor(
    private val repository: DataAlcoholRepository
) {
    suspend operator fun invoke(category: String): Flow<List<AlcoholEntity>> {
        return repository.getAlcoholData(category = category)
    }
}