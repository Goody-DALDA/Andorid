package com.oyj.domain.usecase.login.pref

import com.oyj.domain.model.ProfileEntity
import com.oyj.domain.repository.LoginRepository
import javax.inject.Inject

class SetProfileUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    operator fun invoke(profileEntity: ProfileEntity) {
        repository.setProfile(profileEntity)
    }
}