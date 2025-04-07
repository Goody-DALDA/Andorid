package com.goody.dalda.ui.confetti

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goody.dalda.data.model.toAppModel
import com.oyj.domain.usecase.login.FetchProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfettiViewModel @Inject constructor(
    private val fetchProfileUseCase: FetchProfileUseCase
) : ViewModel() {
    fun fetchProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            val profile = fetchProfileUseCase().toAppModel()
        }
    }
}
