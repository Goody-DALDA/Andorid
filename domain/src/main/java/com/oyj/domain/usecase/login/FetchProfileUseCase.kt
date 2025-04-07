package com.oyj.domain.usecase.login

import com.oyj.domain.model.ProfileEntity
import com.oyj.domain.repository.LoginRepository
import javax.inject.Inject

class FetchProfileUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(): ProfileEntity {
        return loginRepository.fetchProfile()
    }
}