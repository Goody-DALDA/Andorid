package com.goody.dalda.ui.undefined

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.goody.dalda.base.BaseFragment
import com.goody.dalda.databinding.FragmentUndefinedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UndefinedFragment : BaseFragment<FragmentUndefinedBinding>() {

    private val viewModel: UndefinedViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUndefinedBinding
        get() = FragmentUndefinedBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.text.observe(viewLifecycleOwner) {
            binding.textUndefined.text = it
        }
    }
}