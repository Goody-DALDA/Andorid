package com.oyj.domain.usecase.login.pref

import com.oyj.domain.repository.LoginRepository
import javax.inject.Inject

class IsShowOnboardingUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    operator fun invoke(): Boolean {
        return repository.isShowOnboarding()
    }
}