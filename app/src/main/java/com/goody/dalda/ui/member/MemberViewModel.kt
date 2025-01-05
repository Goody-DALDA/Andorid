package com.goody.dalda.ui.member

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.repository.LoginRepository
import com.goody.dalda.ui.model.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemberViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {

    private val _profileState = mutableStateOf(Profile())
    val profile: State<Profile> get() = _profileState

    fun fetchProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            _profileState.value = repository.getProfile()
        }
    }
}
