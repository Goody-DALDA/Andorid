package com.oyj.domain.usecase.login.pref

import com.oyj.domain.repository.LoginRepository
import javax.inject.Inject

class SetAccessTokenUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    operator fun invoke(token: String) {
        repository.setAccessToken(token)
    }
}