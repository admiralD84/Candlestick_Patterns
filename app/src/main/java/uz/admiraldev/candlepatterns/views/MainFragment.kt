package uz.admiraldev.candlepatterns.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.admiraldev.candlepatterns.R
import uz.admiraldev.candlepatterns.databinding.FragmentMainBinding

class MainFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: FragmentMainBinding
    private val DELAY_TIME: Long = 300
    private val myCoroutine = CoroutineScope(Dispatchers.Main)
    private lateinit var drawerMenu: DrawerLayout
    private var searchView: SearchView? = null
    private lateinit var navView: NavigationView
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        activity?.window?.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.transparent)

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.generalNavFragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> showToolbar(false, destination)
                R.id.aboutFragment -> showToolbar(false, destination)
                else -> {
                    showToolbar(true, destination)
                }
            }
        }
        navView = binding.drawerMenu

        drawerMenu = binding.drawerLayout
        drawerMenu.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView = binding.toolbar.searchView

        searchView?.setOnSearchClickListener {
            binding.toolbar.toolbarTitle.visibility = View.GONE
        }

        searchView?.setOnCloseListener {
            binding.toolbar.toolbarTitle.visibility = View.VISIBLE
            false
        }
        binding.toolbar.navMenuButton.setOnClickListener {
            if (!searchView!!.isIconified)
                searchView?.isIconified = true
            drawerMenu.openDrawer(GravityCompat.START)
            drawerMenu.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
        initClicks()
    }

    private fun initClicks() {

        navView.setNavigationItemSelectedListener(this)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (drawerMenu.isDrawerOpen(GravityCompat.START)) {
                    drawerMenu.closeDrawer(GravityCompat.START)
                } else {
                    requireActivity().moveTaskToBack(true)
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

    }

    private fun showToolbar(show: Boolean, destination: NavDestination) {
        val toolbar = binding.toolbar.customToolbar
        val toolbarTitle = binding.toolbar.toolbarTitle
        val fromRight = AnimationUtils.loadAnimation(context, R.anim.from_left_toolbar)
        if (show) {
            toolbarTitle.text = getToolbarTitle(destination)
            if (toolbar.visibility != View.VISIBLE) {
                myCoroutine.launch {
                    delay(DELAY_TIME)
                    toolbar.startAnimation(fromRight)
                    toolbar.visibility = View.VISIBLE
                }
            }
        } else {
            if (searchView != null) {
                if (!searchView!!.isIconified)
                    searchView!!.isIconified = true
            }
            toolbar.visibility = View.GONE
        }
    }

    private fun getToolbarTitle(destination: NavDestination): String {
        return destination.label?.toString() ?: "Default Title"
    }


    private fun shareApp(packageName: String) {

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, getText(R.string.drawer_menu_share))
            putExtra(
                Intent.EXTRA_TEXT,
                packageName
            )
        }
        startActivity(Intent.createChooser(shareIntent, "Share app"))
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.share_app ||
            item.itemId == R.id.rate_app ||
            item.itemId == R.id.about_app ||
            item.itemId == R.id.donate
        ) {
            when (item.itemId) {
                R.id.share_app -> {
                    val shareText =
                        getText(R.string.share_text).toString() + requireContext().packageName
                    shareApp(shareText)
                }

                R.id.rate_app -> {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(
                                "https://play.google.com/store/apps/details?id="
                                        + requireContext().packageName
                            )
                        )
                    )
                }

                R.id.about_app -> {
                    navController.navigate(R.id.aboutFragment)
                }

                R.id.donate -> {
                    Toast.makeText(requireContext(), "Reklamasiz variant", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        } else {
            NavigationUI.onNavDestinationSelected(item, navController)
        }
        drawerMenu.closeDrawer(GravityCompat.START)
        return true
    }
}