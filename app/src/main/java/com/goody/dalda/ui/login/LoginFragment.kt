package com.goody.dalda.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.goody.dalda.R
import com.goody.dalda.base.BaseFragment
import com.goody.dalda.databinding.FragmentLoginBinding
import com.goody.dalda.ui.confetti.ConfettiFragment
import com.goody.dalda.ui.state.UiState
import com.goody.dalda.util.PreferenceManager
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    companion object {
        fun newInstance() = LoginFragment()
        private const val TAG = "LoginFragment"
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    private val viewModel: LoginViewModel by viewModels()

    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(TAG, "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            requestUserInfo(token)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isNotShowOnboardingScreen = !PreferenceManager.isShowOnboarding()

        if (isNotShowOnboardingScreen) {
            findNavController().navigate(R.id.action_loginFragment_to_onboardingFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentLoginKakaoBtn.setOnClickListener { requestKakaoLogin() }
        binding.fragmentLoginSkipBtn.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_navigation_home) }

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Empty -> TODO()
                is UiState.Error -> {
                    Toast.makeText(context, "로그인에 실패하였습니다.", Toast.LENGTH_LONG).show()
                }
                is UiState.Loading -> TODO()
                is UiState.Success -> {
                    val profile = state.data

                    if (profile.isShowConfettiScreen) {
                        // 신규 회원
                        findNavController().navigate(
                            R.id.action_loginFragment_to_confettiFragment,
                            bundleOf(
                                ConfettiFragment.NICKNAME_KEY to profile.nickname,
                                ConfettiFragment.PROFILE_IMAGE_KEY to profile.profileImg
                            )
                        )
                    } else {
                        // 기존 회원
                        findNavController().navigate(R.id.action_loginFragment_to_navigation_home)
                    }
                }
                is UiState.Uninitialized -> TODO()
            }
        }
    }

    /**
     * 로그인 검사 로직 없이 Main 갔다가 로그인 화면 띄워주고
     * 로그인, 스킵 처리할 경우 pop 처리로 갱신 처리
     */
    private fun requestKakaoLogin() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(binding.root.context)) {
            UserApiClient.instance.loginWithKakaoTalk(binding.root.context) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)

                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    UserApiClient.instance.loginWithKakaoAccount(
                        binding.root.context,
                        callback = callback
                    )
                } else if (token != null) {
                    requestUserInfo(token)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(
                binding.root.context,
                callback = callback
            )
        }
    }


    private fun requestUserInfo(token: OAuthToken) {
        Log.e(TAG, "사용자 정보 요청 실패 ${token.accessToken}")
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e(TAG, "사용자 정보 요청 실패", error)
            }
            else if (user != null) {
                Log.i(TAG, "사용자 정보 요청 성공" +
                        "\n회원번호: ${user.id}" +
                        "\n이메일: ${user.kakaoAccount?.email}" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")

                val nickname = user.kakaoAccount?.profile?.nickname ?: ""
                val email = user.kakaoAccount?.email ?: ""
                val thumbnail = user.kakaoAccount?.profile?.thumbnailImageUrl ?: ""

                viewModel.login(nickname, email, thumbnail, token)
            }
        }
    }
}