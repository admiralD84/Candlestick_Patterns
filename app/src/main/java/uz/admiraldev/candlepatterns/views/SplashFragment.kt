package uz.admiraldev.candlepatterns.views

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.admiraldev.candlepatterns.R
import uz.admiraldev.candlepatterns.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var splashBinding: FragmentSplashBinding
    private val DELAY_TIME: Long = 1500
    private val myCoroutine = CoroutineScope(Dispatchers.Main)

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        splashBinding = FragmentSplashBinding.inflate(inflater, container, false)
        myCoroutine.launch {
            delay(DELAY_TIME)
            findNavController().navigate(
                R.id.action_splashFragment_to_mainFragment
            )
        }
        return splashBinding.root
    }
}