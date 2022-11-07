package com.sky.skyoverflow


import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.sky.skyoverflow.Authentication.Login
import com.sky.skyoverflow.SharedPerfence.MyPreferences
import com.sky.skyoverflow.SharedPerfence.PrefConf
import com.sky.skyoverflow.Utils.AppUtils
import com.sky.skyoverflow.Utils.LoadingDialog
import com.sky.skyoverflow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.inculde_layout.view.*
import kotlinx.android.synthetic.main.version_layout.view.*
import java.io.File


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MenuItem.OnMenuItemClickListener, View.OnClickListener,
    DrawerLayout.DrawerListener {
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
        binding.includedLayout.txtName.text =
            MyPreferences.getInstance(this).getString(PrefConf.USER_NAME, "GUEST")

        appBarConfiguration =
            AppBarConfiguration.Builder(navController.graph).setDrawerLayout(binding.drawer).build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navigationView, navController);
        NavigationUI.setupWithNavController(
            binding.includedLayout.toolbar, navController, binding.drawer
        );
        binding.includedLayout.toolbar.navigationIcon =
            resources.getDrawable(R.drawable.ic_menu_icon)
        NavigationUI.setupWithNavController(binding.includedLayout.bottomNavigation, navController)

        binding.drawer.addDrawerListener(this)

        navController?.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.Dashboard) {
                binding.includedLayout.relative.visibility = View.VISIBLE
                binding.includedLayout.toolbar.navigationIcon =
                    resources.getDrawable(R.drawable.ic_menu_icon)
                binding.includedLayout.txtName.visibility = View.VISIBLE
                binding.includedLayout.txtWel.visibility = View.VISIBLE
                binding.includedLayout.txtLebel.visibility = View.GONE
                binding.includedLayout.imgOrderHistory.visibility = View.GONE
                binding.includedLayout.imgCart.visibility = View.GONE
                binding.includedLayout.bottomNavigation.visibility = View.VISIBLE
            } else if (destination.id == R.id.walletFragment || destination.id == R.id.profileFragment || destination.id == R.id.accountSettingFragment
                || destination.id == R.id.transfer_Fund_Fragment || destination.id == R.id.kycVerificationFragment || destination.id == R.id.uploadPhotoFragment
                || destination.id == R.id.uploadDocFragment || destination.id == R.id.changePasswordFragment || destination.id == R.id.addBankFragment
                || destination.id == R.id.editProfileFragment || destination.id == R.id.supportFragment || destination.id == R.id.mobileRechargeFragment
                || destination.id == R.id.DTHRechargeFragment || destination.id == R.id.membeActivitionFragment || destination.id == R.id.shippingAddressFragment
                || destination.id == R.id.deliveryAddressFragment || destination.id == R.id.paymentModeFragment || destination.id == R.id.orderHistoryFragment
                || destination.id == R.id.totalEarningsFragment || destination.id == R.id.incomeFragment || destination.id == R.id.reHistoryFragment || destination.id == R.id.BillFragment
            ) {
                binding.includedLayout.relative.visibility = View.VISIBLE
                binding.includedLayout.toolbar.navigationIcon =
                    resources.getDrawable(R.drawable.ic_back_arrow)
                binding.includedLayout.txtName.visibility = View.GONE
                binding.includedLayout.imgCart.visibility = View.GONE
                binding.includedLayout.txtWel.visibility = View.GONE
                binding.includedLayout.txtLebel.visibility = View.VISIBLE
                binding.includedLayout.imgOrderHistory.visibility = View.GONE
                binding.includedLayout.bottomNavigation.visibility = View.GONE
                if (destination.label.toString().equals("{title}", true)) {
                    binding.includedLayout.txtLebel.text =
                        binding.includedLayout.toolbar.title.toString()
                } else {
                    binding.includedLayout.txtLebel.text = destination.label
                }
                Log.d("destination", binding.includedLayout.toolbar.title.toString())
            } else if (destination.id == R.id.memberSuccessFragment) {
                binding.includedLayout.relative.visibility = View.GONE
                binding.includedLayout.imgCart.visibility = View.GONE
                binding.includedLayout.imgOrderHistory.visibility = View.GONE
                binding.includedLayout.bottomNavigation.visibility = View.GONE
            } else if (destination.id == R.id.RepurchaseFragment) {
                binding.includedLayout.relative.visibility = View.VISIBLE
                binding.includedLayout.toolbar.navigationIcon =
                    resources.getDrawable(R.drawable.ic_back_arrow)
                binding.includedLayout.txtName.visibility = View.GONE
                binding.includedLayout.txtWel.visibility = View.GONE
                binding.includedLayout.txtLebel.visibility = View.VISIBLE
                binding.includedLayout.txtLebel.text = destination.label
                binding.includedLayout.imgCart.visibility = View.VISIBLE
                binding.includedLayout.imgOrderHistory.visibility = View.GONE
                binding.includedLayout.bottomNavigation.visibility = View.GONE

            } else if (destination.id == R.id.yourCartFragment) {
                binding.includedLayout.relative.visibility = View.VISIBLE
                binding.includedLayout.toolbar.navigationIcon =
                    resources.getDrawable(R.drawable.ic_back_arrow)
                binding.includedLayout.txtName.visibility = View.GONE
                binding.includedLayout.txtWel.visibility = View.GONE
                binding.includedLayout.txtLebel.visibility = View.VISIBLE
                binding.includedLayout.txtLebel.text = destination.label
                binding.includedLayout.imgCart.visibility = View.GONE
                binding.includedLayout.imgOrderHistory.visibility = View.VISIBLE
                binding.includedLayout.bottomNavigation.visibility = View.GONE

            } else {
                binding.includedLayout.relative.visibility = View.VISIBLE
                binding.includedLayout.toolbar.navigationIcon =
                    resources.getDrawable(R.drawable.ic_back_arrow)
                binding.includedLayout.txtName.visibility = View.GONE
                binding.includedLayout.txtWel.visibility = View.GONE
                binding.includedLayout.imgCart.visibility = View.GONE
                binding.includedLayout.txtLebel.visibility = View.VISIBLE
                binding.includedLayout.txtLebel.text = destination.label
                binding.includedLayout.imgOrderHistory.visibility = View.GONE
                binding.includedLayout.bottomNavigation.visibility = View.VISIBLE
            }
        }
        moreNavigationOptions()
        binding.includedLayout.imgCart.setOnClickListener(this)
        binding.includedLayout.imgOrderHistory.setOnClickListener(this)
    }

    private fun moreNavigationOptions() {
        val menu: Menu = binding.navigationView.getMenu()

        val logout: MenuItem = menu.findItem(R.id.nav_logout)
        logout.setOnMenuItemClickListener(this)
        val versions: MenuItem = menu.findItem(R.id.version_layout)
        versions.setOnMenuItemClickListener(this)
        var version: CardView = versions.actionView!!.findViewById(R.id.close_menu)
        version.setOnClickListener(this)

        var navHeader: View = binding.navigationView.getHeaderView(0)
        navHeader.findViewById<TextView>(R.id.txt_name).text =
            MyPreferences.getInstance(this).getString(PrefConf.USER_NAME, "GUEST")
        navHeader.findViewById<TextView>(R.id.txt_mobile).text =
            MyPreferences.getInstance(this).getString(PrefConf.USER_MOBILE, "9999999999")

    }


    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController, appBarConfiguration
        )//navController.navigateUp() || super.onSupportNavigateUp()
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

    override fun onMenuItemClick(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.nav_logout -> {
                LogoutAlertBox()
            }
            R.id.version_layout -> {
                if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
                    binding.drawer.closeDrawer(GravityCompat.START);
                }
            }
        }
        return true
    }

    private fun LogoutAlertBox() {

        val alertDialogBuilder = AlertDialog.Builder(this@MainActivity)

        alertDialogBuilder.setTitle(resources.getString(R.string.app_name))

        alertDialogBuilder.setIcon(R.mipmap.ic_launcher_round)
        alertDialogBuilder.setMessage("Are you sure to Logout !!!!!").setCancelable(false)
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, id ->
                MyPreferences.getInstance(this@MainActivity).clearPreferences()
                clearApplicationData()
                Toast.makeText(this@MainActivity, "Logout Successfully", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Login::class.java))
                finish()
            }).setNegativeButton("No", { dialog, id -> dialog.cancel() })

        // create alert dialog
        val alertDialog: AlertDialog = alertDialogBuilder.create()

        alertDialog.show()
    }

    @SuppressLint("LongLogTag")
    fun clearApplicationData() {
        val cache: File = cacheDir
        val appDir = File(cache.getParent())
        if (appDir.exists()) {
            val children: Array<String> = appDir.list()
            for (s in children) {
                if (s != "lib") {
                    deleteDir(File(appDir, s))
                    Log.i(
                        "EEEEEERRRRRRROOOOOOORRRR",
                        "**************** File /data/data/APP_PACKAGE/$s DELETED *******************"
                    )
                }
            }
        }
    }

    fun deleteDir(dir: File?): Boolean {
        if (dir != null && dir.isDirectory()) {
            val children: Array<String> = dir.list()
            for (i in children.indices) {
                val success = deleteDir(File(dir, children[i]))
                if (!success) {
                    return false
                }
            }
        }
        return dir!!.delete()
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {

            R.id.close_menu -> {
                if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
                    binding.drawer.closeDrawer(GravityCompat.START);
                }
            }

            R.id.img_cart -> {
                navController.navigate(R.id.yourCartFragment)
            }

            R.id.img_orderHistory -> {
                navController.navigate(R.id.orderHistoryFragment)
            }
        }
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

    }

    override fun onDrawerOpened(drawerView: View) {
        changeStatusBarColor()
    }

    override fun onDrawerClosed(drawerView: View) {
        AppUtils.setStatusBarGradiant(this)
    }

    override fun onDrawerStateChanged(newState: Int) {

    }

    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(Color.TRANSPARENT)
        }
    }
}