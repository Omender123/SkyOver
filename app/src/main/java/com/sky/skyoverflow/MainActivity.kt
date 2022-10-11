package com.sky.skyoverflow


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.sky.skyoverflow.Utils.LoadingDialog
import com.sky.skyoverflow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.inculde_layout.view.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var loadingDialog: LoadingDialog
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadingDialog = LoadingDialog(this)

        navController = findNavController(R.id.main_fragment)
        setSupportActionBar(binding.includedLayout.toolbar);

        appBarConfiguration = AppBarConfiguration.Builder(navController.graph)
            .setDrawerLayout(binding.drawer)
            .build()
        NavigationUI.setupWithNavController(binding.navigationView, navController);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(
            binding.includedLayout.toolbar,
            navController,
            binding.drawer
        );
        binding.includedLayout.toolbar.navigationIcon =
            resources.getDrawable(R.drawable.ic_menu_icon)
        NavigationUI.setupWithNavController(binding.includedLayout.bottomNavigation, navController)

        navController?.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.Dashboard ) {
                binding.includedLayout.toolbar.navigationIcon =
                    resources.getDrawable(R.drawable.ic_menu_icon)
                binding.includedLayout.bottomNavigation.visibility = View.VISIBLE
            }
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.label == "Dashboard") {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    fun showLoadingDialog() {
        if (this::loadingDialog.isInitialized && !loadingDialog.isShowing()) {
            loadingDialog.show()
        }
    }

    fun hideLoadingDialog() {
        if (this::loadingDialog.isInitialized && loadingDialog.isShowing()) {
            loadingDialog.dismiss()
        }
    }


}