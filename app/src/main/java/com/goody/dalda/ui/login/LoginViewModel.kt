package com.goody.dalda.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    companion object {
        private const val TAG = "LoginViewModel"
    }

    private val _state = MutableLiveData<UiState<Boolean>>()
    val state: LiveData<UiState<Boolean>> get() = _state

    /**
     * 서버에서 회원가입인지 로그인인지 키값을 내려 컴페티 표시 화면 관리하면 될듯
     */
    fun login(accessToken: String) {
        Log.i(TAG, "카카오계정으로 로그인 성공 ${accessToken}")
        viewModelScope.launch {
            val isShowConfettiScreen = true
            _state.postValue(UiState.Success(isShowConfettiScreen))
        }
    }
}