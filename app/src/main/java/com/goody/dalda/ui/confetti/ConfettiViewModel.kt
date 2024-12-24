package com.goody.dalda.ui.confetti

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfettiViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel() {
    fun fetchProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            val profile = repository.getProfile()
        }
    }
}