package uz.admiraldev.candlepatterns.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.admiraldev.candlepatterns.R
import uz.admiraldev.candlepatterns.databinding.FragmentBasicsBinding
import uz.admiraldev.candlepatterns.databinding.FragmentBearishPatternsBinding

class BearishPatternsFragment : Fragment() {
    private var binding: FragmentBearishPatternsBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBearishPatternsBinding.inflate(inflater, container, false)
        initClicks()
        return binding!!.root
    }

    private fun initClicks() {

    }
}