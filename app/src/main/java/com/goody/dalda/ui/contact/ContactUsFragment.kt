package com.goody.dalda.ui.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.goody.dalda.base.BaseFragment
import com.goody.dalda.databinding.FragmentContactUsBinding

class ContactUsFragment : BaseFragment<FragmentContactUsBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentContactUsBinding
        get() = FragmentContactUsBinding::inflate

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentContactUsComposeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ContactUsScreen()
            }
        }
    }
}
