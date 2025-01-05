package com.goody.dalda.ui.member

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.goody.dalda.base.BaseFragment
import com.goody.dalda.databinding.FragmentMemberBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemberFragment: BaseFragment<FragmentMemberBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMemberBinding
        get() = FragmentMemberBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentMemberComposeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MemberScreen()
            }
        }
    }
}