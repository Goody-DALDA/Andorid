package com.oyj.domain.usecase.login

import com.oyj.domain.model.ResultMessageEntity
import com.oyj.domain.repository.LoginRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: LoginRepository,
) {
    suspend operator fun invoke(): ResultMessageEntity {
        return repository.logout()
    }
}