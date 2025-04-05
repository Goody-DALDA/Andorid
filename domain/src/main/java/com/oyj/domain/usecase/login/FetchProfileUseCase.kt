package com.oyj.domain.usecase.login

import com.oyj.domain.model.ProfileDomain
import com.oyj.domain.repository.LoginRepository
import javax.inject.Inject

class FetchProfileUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(): ProfileDomain {
        return loginRepository.getFetchProfile()
    }
}