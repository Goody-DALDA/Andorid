package com.oyj.domain.usecase

import com.oyj.domain.model.BlogEntity
import com.oyj.domain.repository.BlogRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBlogDataListUseCase @Inject constructor(
    private val repository: BlogRepository
) {
    suspend operator fun invoke(query: String): Flow<List<BlogEntity>> {
        return repository.getBlogDataList(query)
    }
}