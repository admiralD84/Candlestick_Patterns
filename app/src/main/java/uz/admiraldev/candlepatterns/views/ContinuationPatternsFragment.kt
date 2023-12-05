package uz.admiraldev.candlepatterns.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import uz.admiraldev.candlepatterns.R
import uz.admiraldev.candlepatterns.databinding.FragmentContinutionPatternsBinding

class ContinuationPatternsFragment : Fragment() {
    private var binding: FragmentContinutionPatternsBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContinutionPatternsBinding.inflate(inflater, container, false)
        initClicks()
        return binding!!.root
    }

    private fun initClicks() {

    }
}