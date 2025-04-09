package com.oyj.domain.usecase.login

import com.oyj.domain.model.ResultMessageEntity
import com.oyj.domain.repository.LoginRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class LogoutUseCase @Inject constructor(
    private val repository: LoginRepository,
) {
    suspend operator fun invoke(): Flow<ResultMessageEntity> {
        return repository.logout()
    }
}