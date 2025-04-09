package com.oyj.domain.usecase.bookmark

import com.oyj.domain.model.AlcoholEntity
import com.oyj.domain.repository.DataAlcoholRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsBookmarkAlcoholUseCase  @Inject constructor(
    private val dataAlcoholRepository: DataAlcoholRepository,
) {
    operator fun invoke(alcoholEntity: AlcoholEntity): Flow<Boolean> {
        return dataAlcoholRepository.isBookmarkAlcohol(alcoholEntity)
    }
}