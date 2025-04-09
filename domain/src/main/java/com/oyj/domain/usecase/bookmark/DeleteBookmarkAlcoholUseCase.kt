package com.oyj.domain.usecase.bookmark

import com.oyj.domain.model.AlcoholEntity
import com.oyj.domain.repository.AlcoholRepository
import javax.inject.Inject

class DeleteBookmarkAlcoholUseCase @Inject constructor(
    private val alcoholRepository: AlcoholRepository,
) {
    suspend operator fun invoke(alcoholEntity: AlcoholEntity) {
        alcoholRepository.deleteBookmarkAlcohol(alcoholEntity)
    }
}