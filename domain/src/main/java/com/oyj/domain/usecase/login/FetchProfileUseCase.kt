package com.oyj.domain.usecase.login

import com.oyj.domain.model.ProfileEntity
import com.oyj.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchProfileUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(): Flow<ProfileEntity> {
        return loginRepository.fetchProfile()
    }
}