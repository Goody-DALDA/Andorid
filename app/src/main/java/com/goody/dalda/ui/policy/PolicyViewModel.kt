package com.goody.dalda.ui.policy

import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

@HiltViewModel
class PolicyViewModel @Inject constructor() : ViewModel() {
    private val _termsOfUseStateNew = MutableStateFlow("")
    val termsOfUseStateNew: StateFlow<String> = _termsOfUseStateNew

    fun fetchTermsOfUse(
        assetManager: AssetManager,
        fileName: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val sb = StringBuilder()
            assetManager.open(fileName).use { inputStream ->
                val reader = BufferedReader(InputStreamReader(inputStream))
                var line: String? = null

                while ((reader.readLine()?.also { line = it }) != null) {
                    sb.append(line).append("\n")
                }
            }
            _termsOfUseStateNew.value = sb.toString()
        }
    }
}
