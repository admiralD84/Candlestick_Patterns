package uz.admiraldev.candlepatterns.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.admiraldev.candlepatterns.MyApplication
import uz.admiraldev.candlepatterns.R
import uz.admiraldev.candlepatterns.adapters.TermsAdapter
import uz.admiraldev.candlepatterns.databinding.FragmentBasicsBinding
import uz.admiraldev.candlepatterns.models.TradingTerms
import uz.admiraldev.candlepatterns.repositories.AppRepository
import uz.admiraldev.candlepatterns.viewmodels.AppViewModel
import uz.admiraldev.candlepatterns.viewmodels.AppViewModelFactory


class BasicsFragment : Fragment() {
    private lateinit var binding: FragmentBasicsBinding

    //    private val termsList: MutableList<TradingTerms> = mutableListOf()
    private lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasicsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myApplication = requireActivity().application as MyApplication

        val viewModelFactory = AppViewModelFactory(myApplication, AppRepository())
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[AppViewModel::class.java]

        viewModel.tradingTermsLiveData.observe(viewLifecycleOwner) { terms ->
            val adapter = TermsAdapter(terms)
            adapter.setOnTermsClickListener(object : TermsAdapter.OnTermClickListener {
                override fun onTermClick(term: TradingTerms) {
                    viewModel.saveDescription(term.definition)
                    findNavController().navigate(R.id.detailedInfoFragment)
                }
            })
            binding.rvTermsContent.adapter = adapter
        }
    }
}