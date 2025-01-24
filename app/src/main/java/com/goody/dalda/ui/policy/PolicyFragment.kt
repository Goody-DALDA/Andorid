package com.goody.dalda.ui.policy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.navigation.findNavController
import com.goody.dalda.base.BaseFragment
import com.goody.dalda.databinding.FragmentPolicyBinding

class PolicyFragment : BaseFragment<FragmentPolicyBinding>() {
    companion object {
        const val PRIVACY_POLICY = "privacy_policy"
        const val TERMS_OF_USE = "terms_of_use"
        const val FILE_NAME = "file_name"
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPolicyBinding
        get() = FragmentPolicyBinding::inflate

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString(TERMS_OF_USE) ?: arguments?.getString(PRIVACY_POLICY) ?: ""
        val fileName = arguments?.getString(FILE_NAME) ?: ""

        binding.fragmentPolicyComposeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                PolicyScreen(
                    title = title,
                    fileName = fileName,
                    onClose = { findNavController().popBackStack() },
                )
            }
        }
    }
}
