package uz.admiraldev.candlepatterns.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import uz.admiraldev.candlepatterns.adapters.TermsAdapter
import uz.admiraldev.candlepatterns.databinding.FragmentBasicsBinding
import uz.admiraldev.candlepatterns.models.TradingTerms

class BasicsFragment : Fragment() {
    private lateinit var binding: FragmentBasicsBinding
    private val termsList: MutableList<TradingTerms> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasicsBinding.inflate(inflater, container, false)
        termsList.add(
            TradingTerms(
                term = "Ордер на продажу",
                englishName = "Sell Order",
                definition = "bla bla bla bla bla bla bla bla bla"
            )
        )
        termsList.add(
            TradingTerms(
                term = "Стоп-лосс",
                englishName = "Stop-Loss",
                definition = "bla bla bla bla bla bla bla bla bla"
            )
        )
        termsList.add(
            TradingTerms(
                term = "Диверсификация",
                englishName = "Diversification",
                definition = "bla bla bla bla bla bla bla bla bla"
            )
        )
//        initClicks()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contentAdapter = TermsAdapter(termsList)
        binding.rvTermsContent.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTermsContent.adapter = contentAdapter
    }

    private fun initClicks() {


    }
}