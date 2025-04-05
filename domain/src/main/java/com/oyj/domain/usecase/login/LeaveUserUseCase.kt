package com.oyj.domain.usecase.login

import com.oyj.domain.model.ResultMessageDomain
import com.oyj.domain.repository.LoginRepository
import javax.inject.Inject

class LeaveUserUseCase @Inject constructor(
    private val repository: LoginRepository,
) {
    suspend operator fun invoke(): ResultMessageDomain {
        return repository.leaveUser()
    }
}