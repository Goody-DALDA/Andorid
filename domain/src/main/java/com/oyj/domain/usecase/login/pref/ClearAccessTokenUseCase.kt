package com.oyj.domain.usecase.login.pref

import com.oyj.domain.model.ProfileDomain
import com.oyj.domain.repository.LoginRepository
import javax.inject.Inject

class ClearAccessTokenUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    operator fun invoke() {
        repository.clearAccessToken()
    }
}