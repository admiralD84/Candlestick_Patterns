package uz.admiraldev.candlepatterns.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.admiraldev.candlepatterns.R
import uz.admiraldev.candlepatterns.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val scaleAnimation = ScaleAnimation(
            0.5f,
            0.97f,
            0.5f,
            0.97f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        scaleAnimation.duration = 1000
        scaleAnimation.fillAfter = true
        binding.glSections.startAnimation(scaleAnimation)
        initClicks()
        return binding.root
    }

    private fun initClicks() {
        binding.cardBasics.setOnClickListener {
            findNavController().navigate(R.id.basicsFragment)
        }
        binding.cardIndicators.setOnClickListener {
            findNavController().navigate(R.id.indicatorsFragment)
        }
        binding.cardBullishPatterns.setOnClickListener {
            findNavController().navigate(R.id.bullishPatternsFragment)
        }
        binding.cardBearishPatterns.setOnClickListener {
            findNavController().navigate(R.id.bearishPatternsFragment)
        }
        binding.cardContinueTrend.setOnClickListener {
            findNavController().navigate(R.id.continuationPatternsFragment)
        }
        binding.cardReverseTrend.setOnClickListener {
            findNavController().navigate(R.id.reversalPatternsFragment)
        }
    }
}