package com.oyj.domain.usecase.bookmark

import com.oyj.domain.model.AlcoholEntity
import com.oyj.domain.repository.AlcoholRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkAlcoholListUseCase @Inject constructor(
    private val alcoholRepository: AlcoholRepository,
) {
    suspend operator fun invoke(): Flow<List<AlcoholEntity>> {
        return alcoholRepository.getBookmarkAlcoholList()
    }
}