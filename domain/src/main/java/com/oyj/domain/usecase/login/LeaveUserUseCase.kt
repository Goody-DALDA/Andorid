package com.oyj.domain.usecase.login

import com.oyj.domain.model.ResultMessageEntity
import com.oyj.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LeaveUserUseCase @Inject constructor(
    private val repository: LoginRepository,
) {
    suspend operator fun invoke(): Flow<ResultMessageEntity> {
        return repository.leaveUser()
    }
}