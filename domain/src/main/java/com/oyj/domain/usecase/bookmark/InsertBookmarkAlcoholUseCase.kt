package com.oyj.domain.usecase.bookmark

import com.oyj.domain.Alcohol
import com.oyj.domain.repository.AlcoholRepository
import javax.inject.Inject

class InsertBookmarkAlcoholUseCase @Inject constructor(
    private val alcoholRepository: AlcoholRepository,
) {
    suspend operator fun invoke(alcohol: Alcohol) {
        alcoholRepository.insertBookmarkAlcohol(alcohol)
    }
}