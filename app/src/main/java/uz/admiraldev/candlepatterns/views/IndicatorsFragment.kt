package uz.admiraldev.candlepatterns.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.admiraldev.candlepatterns.adapters.IndicatorsAdapter
import uz.admiraldev.candlepatterns.databinding.FragmentIndicatorsBinding
import uz.admiraldev.candlepatterns.models.TradingIndicator

class IndicatorsFragment : Fragment() {
    private lateinit var binding: FragmentIndicatorsBinding
    private val indicators: MutableList<TradingIndicator> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIndicatorsBinding.inflate(inflater, container, false)

        indicators.add(
            TradingIndicator(
                name = "Oddiy harakatlanuvchi oʻrtacha",
                englishName = "Simple Moving Average (SMA)",
                description = "lorem Ips bla bla bla bla"
            )
        )
        indicators.add(
            TradingIndicator(
                name = "Harakatlanuvchi oʻrtacha konvergentsiya farqi",
                englishName = "Moving Average Convergence Divergence (MACD)",
                description = "lorem Ips bla bla bla bla"
            )
        )
        indicators.add(
            TradingIndicator(
                name = " Oʻrtacha haqiqiy diapazon",
                englishName = "Average True Range (ATR)",
                description = "lorem Ips bla bla bla bla"
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contentAdapter = IndicatorsAdapter(indicators)
        binding.rvIndicatorsContent.adapter = contentAdapter
    }
}