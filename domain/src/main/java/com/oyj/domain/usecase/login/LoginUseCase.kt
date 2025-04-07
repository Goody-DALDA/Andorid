package com.oyj.domain.usecase.login

import com.oyj.domain.model.OAuthTokenEntity
import com.oyj.domain.model.ProfileEntity
import com.oyj.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(
        nickname: String,
        email: String,
        profileImg: String,
        token: OAuthTokenEntity,
    ): ProfileEntity? {
        return repository.login(
            nickname = nickname,
            email = email,
            profileImg = profileImg,
            token = token
        )
    }
}