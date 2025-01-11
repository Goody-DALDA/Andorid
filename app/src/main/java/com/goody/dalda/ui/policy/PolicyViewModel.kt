package com.goody.dalda.ui.policy

import android.content.res.AssetManager
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

@HiltViewModel
class PolicyViewModel @Inject constructor() : ViewModel() {

    companion object {
        private const val TERMS_OF_USER_FILE = "terms_of_use.txt"
    }

    private val termsOfUseState = mutableStateOf("")

    fun fetchTermsOfUse(assetManager: AssetManager) {
        viewModelScope.launch(Dispatchers.IO) {
            val sb = StringBuilder()
            assetManager.open(TERMS_OF_USER_FILE).use { inputStream ->
                val reader = BufferedReader(InputStreamReader(inputStream))
                var line: String? = null

                while ((reader.readLine()?.also { line = it }) != null) {
                    sb.append(line).append("\n")
                }
            }

            termsOfUseState.value = sb.toString()
        }

    }

    fun getTermsOfUseContent() = termsOfUseState.value
}
