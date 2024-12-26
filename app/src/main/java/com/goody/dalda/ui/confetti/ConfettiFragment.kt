package com.goody.dalda.ui.confetti

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.goody.dalda.R
import com.goody.dalda.base.BaseFragment
import com.goody.dalda.databinding.FragmentConfettiBinding
import dagger.hilt.android.AndroidEntryPoint
import nl.dionsegijn.konfetti.core.Angle
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.Spread
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class ConfettiFragment : BaseFragment<FragmentConfettiBinding>() {

    companion object {
        const val NICKNAME_KEY = "nickname"
        const val PROFILE_IMAGE_KEY = "profile_image"
        fun newInstance() = ConfettiFragment()
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentConfettiBinding
        get() = FragmentConfettiBinding::inflate

    private val viewModel: ConfettiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nickname = arguments?.getString(NICKNAME_KEY)
        val profileImage = arguments?.getString(PROFILE_IMAGE_KEY)
        binding.fragmentConfettiWelcomeText.text = getString(R.string.confetti_welcome_text, nickname)
        binding.fragmentConfettiImageView
        binding.fragmentConfettiConfirm.setOnClickListener {
            findNavController().navigate(R.id.action_confettiFragment_to_navigation_home)
        }
        showConfetti()
    }

    private fun showConfetti() {
        binding.fragmentConfettiKonfettiView.start(parade())
    }

    private fun parade(): List<Party> {
        val party = Party(
            speed = 10f,
            maxSpeed = 30f,
            damping = 0.9f,
            angle = Angle.RIGHT - 45,
            spread = Spread.SMALL,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 2, TimeUnit.SECONDS).perSecond(30),
            position = Position.Relative(0.0, 0.2)
        )

        return listOf(
            party,
            party.copy(
                angle = party.angle - 90, // flip angle from right to left
                position = Position.Relative(1.0, 0.2)
            ),
        )
    }
}