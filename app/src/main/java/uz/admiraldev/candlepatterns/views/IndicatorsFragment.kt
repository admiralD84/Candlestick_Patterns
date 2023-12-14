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
import uz.admiraldev.candlepatterns.adapters.IndicatorsAdapter
import uz.admiraldev.candlepatterns.databinding.FragmentIndicatorsBinding
import uz.admiraldev.candlepatterns.models.TradingIndicator
import uz.admiraldev.candlepatterns.repositories.AppRepository
import uz.admiraldev.candlepatterns.viewmodels.AppViewModel
import uz.admiraldev.candlepatterns.viewmodels.AppViewModelFactory

class IndicatorsFragment : Fragment() {
    private lateinit var binding: FragmentIndicatorsBinding
    private lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIndicatorsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myApplication = requireActivity().application as MyApplication

        val viewModelFactory = AppViewModelFactory(myApplication, AppRepository())
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[AppViewModel::class.java]

        viewModel.tradingIndicatorsLiveData.observe(viewLifecycleOwner) { indicators ->
            val adapter = IndicatorsAdapter(indicators)
            adapter.setOnIndicatorClickListener(object :
                IndicatorsAdapter.OnIndicatorClickListener {
                override fun onIndicatorClick(indicator: TradingIndicator) {
                    viewModel.saveDescription(indicator.description)
                    findNavController().navigate(R.id.detailedInfoFragment)
                }
            })
            binding.rvIndicatorsContent.adapter = adapter
        }
    }
}