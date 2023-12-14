package uz.admiraldev.candlepatterns.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import uz.admiraldev.candlepatterns.MyApplication
import uz.admiraldev.candlepatterns.databinding.FragmentDetailedInfoBinding
import uz.admiraldev.candlepatterns.repositories.AppRepository
import uz.admiraldev.candlepatterns.viewmodels.AppViewModel
import uz.admiraldev.candlepatterns.viewmodels.AppViewModelFactory

class DetailedInfoFragment : Fragment() {
    private lateinit var viewModel: AppViewModel
    private lateinit var binding: FragmentDetailedInfoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailedInfoBinding.inflate(inflater, container, false)
        val myApplication = requireActivity().application as MyApplication
        val viewModelFactory = AppViewModelFactory(myApplication, AppRepository())
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[AppViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedItemDescription.observe(viewLifecycleOwner) { description ->
            binding.wvDetailedInfo.loadData(description, "text/html", "UTF-8")
        }
    }
}