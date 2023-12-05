package uz.admiraldev.candlepatterns.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.admiraldev.candlepatterns.adapters.PatternAdapter
import uz.admiraldev.candlepatterns.databinding.FragmentBullishBinding
import uz.admiraldev.candlepatterns.models.Patterns

class BullishPatternsFragment : Fragment() {
    private lateinit var binding: FragmentBullishBinding
    private val patterns: MutableList<Patterns> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBullishBinding.inflate(inflater, container, false)
        patterns.add(
            Patterns(
                patternName = "Buqa bolg'a",
                patternType = "Candle pattern",
                patternEnglishName = "Bullish hummer",
                description = "Odatda narx teskari tomonga qarab o'zgarishini ko'rsatadi",
                category = "Bullish",
                patternTitleImage = "https://www.forexsrovnavac.cz/assets/img/candle/bullish-hammer-candle.png"
            )
        )
        patterns.add(
            Patterns(
                patternName = "Buqa yutib yuborish",
                patternType = "Candle pattern",
                patternEnglishName = "Bullish engulfing",
                description = "Odatda narx teskari tomonga qarab o'zgarishini ko'rsatadi",
                category = "Bullish",
                patternTitleImage = "https://a.c-dn.net/b/02QUyr/trading-the-bullish-engulfing-candle_body_Stockbullishengulfingpatternlargerfinalfinal.png.full.png"
            )
        )
        patterns.add(
            Patterns(
                patternName = "Buqa Harami",
                patternType = "Candle pattern",
                patternEnglishName = "Bullish Harami",
                description = "Odatda narx teskari tomonga qarab o'zgarishini ko'rsatadi",
                category = "Bullish",
                patternTitleImage = "https://livetouring.org/wp-content/uploads/2021/10/bychja-harami.png"
            )
        )
        patterns.add(
            Patterns(
                patternName = "Uchta oq zobit",
                patternEnglishName = "Three white soldier",
                patternType = "Candle pattern",
                description = "Odatda narx teskari tomonga qarab o'zgarishini ko'rsatadi",
                category = "Bullish",
                patternTitleImage = "https://livetouring.org/wp-content/uploads/2021/10/pattern-tri-belyh-soldata.png"
            )
        )
        initClicks()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val patternAdapter = PatternAdapter(patterns, requireContext())
        binding.rvBullishPatterns.adapter = patternAdapter
    }

    private fun initClicks() {

    }
}