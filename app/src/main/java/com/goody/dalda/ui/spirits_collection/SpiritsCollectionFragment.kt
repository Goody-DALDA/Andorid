package com.goody.dalda.ui.spirits_collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.goody.dalda.base.BaseFragment
import com.goody.dalda.databinding.FragmentSpiritsCollectionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpiritsCollectionFragment : BaseFragment<FragmentSpiritsCollectionBinding>() {

    private val viewModel: SpiritsCollectionViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSpiritsCollectionBinding
        get() = FragmentSpiritsCollectionBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.text.observe(viewLifecycleOwner) {
            binding.textSpiritsCollection.text = it
        }
    }
}
