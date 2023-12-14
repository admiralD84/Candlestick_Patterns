package uz.admiraldev.candlepatterns.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uz.admiraldev.candlepatterns.MyApplication
import uz.admiraldev.candlepatterns.adapters.PatternAdapter
import uz.admiraldev.candlepatterns.databinding.FragmentContinutionPatternsBinding
import uz.admiraldev.candlepatterns.models.Patterns
import uz.admiraldev.candlepatterns.repositories.AppRepository
import uz.admiraldev.candlepatterns.viewmodels.AppViewModel
import uz.admiraldev.candlepatterns.viewmodels.AppViewModelFactory

class ContinuationPatternsFragment : Fragment(), PatternAdapter.OnPatternClickListener {
    private lateinit var binding: FragmentContinutionPatternsBinding
    private lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContinutionPatternsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myApplication = requireActivity().application as MyApplication

        val viewModelFactory = AppViewModelFactory(myApplication, AppRepository())
        viewModel = ViewModelProvider(this, viewModelFactory)[AppViewModel::class.java]
        viewModel.patternsLiveData.observe(viewLifecycleOwner) { patterns ->
            binding.rvContinuePatterns.adapter = PatternAdapter(
                this,
                patterns,
                requireContext()
            )
        }
    }

    override fun onPatternClick(pattern: Patterns) {
        Toast.makeText(requireContext(), "Pattern click", Toast.LENGTH_SHORT).show()
    }
}