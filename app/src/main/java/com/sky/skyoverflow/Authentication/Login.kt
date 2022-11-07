package com.sky.skyoverflow.Authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.sky.skyoverflow.MainActivity
import com.sky.skyoverflow.R
import com.sky.skyoverflow.SharedPerfence.MyPreferences
import com.sky.skyoverflow.SharedPerfence.PrefConf
import com.sky.skyoverflow.Utils.AppUtils
import com.sky.skyoverflow.Utils.LoadingDialog
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.ViewModel.LoginViewModel
import com.sky.skyoverflow.databinding.ActivityLoginBinding
import com.sky.skyoverflow.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login : AppCompatActivity(), View.OnClickListener {
    lateinit var loadingDialog: LoadingDialog
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    private var username: String? = null
    private var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadingDialog = LoadingDialog(this)
        AppUtils.setStatusBarGradiant(this)

        LoginObserveral();

        binding.btnLogin.setOnClickListener(this)
        binding.linearRes.setOnClickListener(this)
        binding.txtForget.setOnClickListener(this)
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


    private fun LoginObserveral() {

        loginViewModel.response.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideLoadingDialog()
                    response.data?.let {
                        MyPreferences.getInstance(this).putBoolean(PrefConf.PREF_SEASON, true);
                        MyPreferences.getInstance(this)
                            .putString(PrefConf.USER_SPONSER_ID, it.Data.UserName);
                        MyPreferences.getInstance(this).putString(
                            PrefConf.USER_NAME,
                            it.Data.FirstName + " " + it.Data.LastName
                        );
                        MyPreferences.getInstance(this)
                            .putString(PrefConf.USER_MOBILE, it.Data.Mobile);
                        MyPreferences.getInstance(this)
                            .putString(PrefConf.USER_EMAIL, it.Data.Email);
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                        Toast.makeText(
                            this,
                            "" + it.Message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }

                is NetworkResult.Error -> {
                    hideLoadingDialog()
                    Toast.makeText(
                        this,
                        response.message,
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.e("Error", response.message.toString())
                }

                is NetworkResult.Loading -> {
                    showLoadingDialog()
                }
            }
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {

            R.id.btn_login -> {
                username = binding.edUser.text.toString().trim()
                password = binding.edPass.text.toString().trim()

                if (username!!.isEmpty()) {
                    binding.edUser.requestFocus()
                    Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show()

                } else if (password!!.isEmpty()) {
                    binding.edPass.requestFocus()
                    Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
                } else {

                      showLoadingDialog()
                      loginViewModel.fetchCheckLoginResponse(username!!, password!!)

                }
            }


            R.id.linear_res -> {
                startActivity(Intent(this, Resgister::class.java))

            }

            R.id.txt_forget -> {
                startActivity(Intent(this, ForgetActivity::class.java))
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}