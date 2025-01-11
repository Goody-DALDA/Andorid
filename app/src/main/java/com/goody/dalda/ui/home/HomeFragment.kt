package com.goody.dalda.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.goody.dalda.base.BaseFragment
import com.goody.dalda.databinding.FragmentHomeBinding
import com.goody.dalda.ui.home.data.Menu
import com.goody.dalda.ui.home.data.Menu.Announcement
import com.goody.dalda.ui.home.data.Menu.ContactUs
import com.goody.dalda.ui.home.data.Menu.PrivacyPolicy
import com.goody.dalda.ui.home.data.Menu.Profile
import com.goody.dalda.ui.home.data.Menu.TermsOfUse
import com.goody.dalda.ui.home.data.Menu.Login
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    companion object {
        private const val CONTACT_US_URL = "https://forms.gle/wJ2fQ53zAasCrHEW7"
    }

    private val viewModel: HomeViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)

            setContent {
                MaterialTheme {
                    HomeScreen(
                        modifier = Modifier,
                        viewModel = viewModel,
                        onClickAlcohol = {
                            findNavController().navigate(
                                HomeFragmentDirections.actionNavigationHomeToCategoryFragment(it)
                            )
                        },
                        onClickSearchBar = {
                            findNavController().navigate(
                                HomeFragmentDirections.actionNavigationHomeToSearchFragment()
                            )
                        },
                        onClickSideMenuItem = { menu ->
                            when (menu) {
                                Announcement -> findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToAnnouncementFragment())
                                ContactUs -> startContactUsForms()
                                PrivacyPolicy -> findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToPolicyFragment())
                                TermsOfUse -> findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToPolicyFragment())
                                Profile -> findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToMemberFragment())
                                Login -> findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToLoginFragment())
                                else -> {}
                            }
                        },
                        onClickSeeLoginScreen = {
                            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToLoginFragment())
                        }
                    )
                }
            }
        }
    }

    /**
     * 문의하기 고도화 할 경우 ContactUsFragment 사용할 예정
     */
    private fun startContactUsForms() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(CONTACT_US_URL))
        startActivity(intent)
    }
}
