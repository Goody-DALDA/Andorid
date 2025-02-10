package com.goody.dalda.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.repository.LoginRepository
import com.goody.dalda.ui.model.Profile
import com.goody.dalda.ui.state.UiState
import com.goody.dalda.util.PreferenceManager
import com.kakao.sdk.auth.model.OAuthToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val repository: LoginRepository,
    ) : ViewModel() {
        private val _state = MutableLiveData<UiState<Profile>>()
        val state: LiveData<UiState<Profile>> get() = _state

        fun checkLogin() {
            val profile = PreferenceManager.getProfile()
            if (profile.email.isNotEmpty()) {
                _state.postValue(UiState.Success(profile))
            }
        }

        fun login(
            nickname: String,
            email: String,
            profileImg: String,
            token: OAuthToken,
        ) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.login(nickname, email, profileImg, token)?.let { profile ->
                    PreferenceManager.setProfile(profile)
                    _state.postValue(UiState.Success(profile))
                }
            }
        }
    }
