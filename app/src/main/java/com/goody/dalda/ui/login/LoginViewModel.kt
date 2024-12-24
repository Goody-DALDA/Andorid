package com.goody.dalda.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.repository.LoginRepository
import com.goody.dalda.ui.model.Profile
import com.goody.dalda.ui.state.UiState
import com.kakao.sdk.auth.model.OAuthToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {
    companion object {
        private const val TAG = "LoginViewModel"
    }

    private val _state = MutableLiveData<UiState<Profile>>()
    val state: LiveData<UiState<Profile>> get() = _state

    /**
     * 서버에서 회원가입인지 로그인인지 키값을 내려 컴페티 표시 화면 관리하면 될듯
     */
    fun login(nickname: String, email: String, profileImg: String, token: OAuthToken) {
        viewModelScope.launch(Dispatchers.IO) {
            val profile = repository.login(nickname, email, profileImg, token)

            if (profile != null) {
                _state.postValue(UiState.Success(profile))
            } else {
                _state.postValue(UiState.Error())
            }
        }
    }
}