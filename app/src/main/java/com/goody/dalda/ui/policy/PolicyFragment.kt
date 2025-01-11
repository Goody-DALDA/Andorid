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

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentPolicyBinding
        get() = FragmentPolicyBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentPolicyComposeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                PolicyScreen(
                    onClose = { findNavController().popBackStack() }
                )
            }
        }
    }
}
