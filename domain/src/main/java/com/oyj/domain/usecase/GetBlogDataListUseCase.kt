package com.oyj.domain.usecase

import com.oyj.domain.model.BlogDataDomain
import com.oyj.domain.repository.BlogRepository
import javax.inject.Inject

class GetBlogDataListUseCase @Inject constructor(private val repository: BlogRepository) {
    suspend operator fun invoke(query: String): List<BlogDataDomain> {
        return repository.getBlogDataList(query)
    }
}