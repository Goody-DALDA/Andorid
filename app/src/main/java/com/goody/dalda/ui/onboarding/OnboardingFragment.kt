package com.goody.dalda.ui.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.goody.dalda.R
import com.goody.dalda.base.BaseFragment
import com.goody.dalda.databinding.FragmentOnboardingBinding
import com.goody.dalda.util.PreferenceManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import java.util.Timer
import java.util.TimerTask

class OnboardingFragment : BaseFragment<FragmentOnboardingBinding>() {
    private val timer = Timer()
    private lateinit var contents: List<OnboardingContents>

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentOnboardingBinding
        get() = FragmentOnboardingBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contents = listOf(
            OnboardingContents(
                R.drawable.img_onboarding_1,
                getString(R.string.onboarding_first_guide)
            ),
            OnboardingContents(
                R.drawable.img_onboarding_2,
                getString(R.string.onboarding_second_guide)
            ),
            OnboardingContents(
                R.drawable.img_onboarding_3,
                getString(R.string.onboarding_third_guide)
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        setupListener()
        autoScrolling()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer.cancel()
    }

    private fun setupViewPager() {
        val adapter = OnboardingAdapter(this, contents)
        binding.fragmentOnboardingViewPager.adapter = adapter

        TabLayoutMediator(
            binding.fragmentOnboardingTabLayout,
            binding.fragmentOnboardingViewPager
        ) { _, _ ->
            /**
             * FIXME : onConfigureTab 함수가 호출 되지 않음
             */
        }.attach()

        binding.fragmentOnboardingTabLayout.addOnTabSelectedListener(object :
            OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let { position ->
                    binding.fragmentOnboardingGuideText.text = contents[position].guideText
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun setupListener() {
        binding.fragmentOnboardingSkipBtn.setOnClickListener {
            PreferenceManager.updateShowOnboarding()
            findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
        }
    }

    private fun autoScrolling() {
        timer.schedule(object : TimerTask() {
            override fun run() {
                val nextItem = binding.fragmentOnboardingViewPager.currentItem + 1
                val itemCount = binding.fragmentOnboardingViewPager.adapter!!.itemCount
                binding.fragmentOnboardingViewPager.post {
                    if (nextItem < itemCount) {
                        binding.fragmentOnboardingViewPager.currentItem = nextItem
                    } else {
                        binding.fragmentOnboardingViewPager.currentItem = 0
                    }
                }
            }
        }, DELAY_MILLISECOND, PERIOD_MILLISECOND)
    }

    class OnboardingAdapter(fragment: Fragment, private val contents: List<OnboardingContents>) :
        FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            return ImageFragment.newInstance(contents[position].imageRes)
        }
    }

    data class OnboardingContents(val imageRes: Int, val guideText: String)

    companion object {
        private const val DELAY_MILLISECOND = 3000L
        private const val PERIOD_MILLISECOND = 3000L

        @JvmStatic
        fun newInstance() = OnboardingFragment().apply {
            arguments = Bundle()
        }
    }
}

