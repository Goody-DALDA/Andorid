package com.oyj.domain.usecase.bookmark

import com.oyj.domain.Alcohol
import com.oyj.domain.repository.AlcoholRepository
import javax.inject.Inject

class IsBookmarkAlcoholUseCase  @Inject constructor(
    private val alcoholRepository: AlcoholRepository,
) {
    suspend operator fun invoke(alcohol: Alcohol): Boolean {
        return alcoholRepository.isBookmarkAlcohol(alcohol)
    }
}