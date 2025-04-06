package com.oyj.domain.usecase

import com.oyj.domain.model.PostDomain
import com.oyj.domain.repository.NoticeRepository
import javax.inject.Inject

class FetchNoticeUseCase  @Inject constructor(
    private val repository: NoticeRepository,
) {
    suspend operator fun invoke(): List<PostDomain> {
        return repository.fetchNotice()
    }
}