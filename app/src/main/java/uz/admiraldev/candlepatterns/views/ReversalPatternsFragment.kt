package uz.admiraldev.candlepatterns.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.admiraldev.candlepatterns.databinding.FragmentReversalPatternsBinding

class ReversalPatternsFragment : Fragment() {
    private var binding: FragmentReversalPatternsBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReversalPatternsBinding.inflate(inflater, container, false)
        return binding!!.root
    }
}