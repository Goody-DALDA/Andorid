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
class PolicyViewModel
@Inject
constructor() : ViewModel() {
    private val termsOfUseState = mutableStateOf("")

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

            termsOfUseState.value = sb.toString()
        }
    }

    fun getTermsOfUseContent() = termsOfUseState.value
}
