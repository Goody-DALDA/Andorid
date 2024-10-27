package com.goody.dalda.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.goody.dalda.base.BaseFragment
import com.goody.dalda.databinding.FragmentImageBinding

private const val ARG_IMAGE_RESOURCE = "args_image_resource"

class ImageFragment : BaseFragment<FragmentImageBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentImageBinding
        get() = FragmentImageBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt(ARG_IMAGE_RESOURCE)?.let { imageRes ->
            binding.fragmentImageImg.load(imageRes)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(imageRes: Int) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_IMAGE_RESOURCE, imageRes)
                }
            }
    }
}