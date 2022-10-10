package com.sky.skyoverflow.Authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.sky.skyoverflow.R
import com.sky.skyoverflow.Utils.LoadingDialog
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.ViewModel.ForgetPasswordViewModel
import com.sky.skyoverflow.ViewModel.LoginViewModel
import com.sky.skyoverflow.databinding.ActivityForgetBinding
import com.sky.skyoverflow.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgetActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var loadingDialog: LoadingDialog
    private lateinit var binding: ActivityForgetBinding
    private val forgetViewModel: ForgetPasswordViewModel by viewModels()
    private var mobile: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  setContentView(R.layout.activity_forget)

        binding = ActivityForgetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadingDialog = LoadingDialog(this)
        binding.btnFor.setOnClickListener(this)
        binding.imgBack.setOnClickListener(this)
        forgetPasswordObserval()
    }


    override fun onClick(p0: View?) {
        when (p0?.id) {

            R.id.btn_for -> {
                mobile = binding.edMobile.text.toString().trim()

                if (mobile.toString().isEmpty()) {
                    Toast.makeText(this, "Please enter Registered Mobile No.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    showLoadingDialog();
                    forgetViewModel.fetchForgetPasswordResponse(mobile.toString())
                }


            }

            R.id.img_back -> {
                onBackPressed()
            }
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

    private fun forgetPasswordObserval() {
        forgetViewModel.ForgetPassword.observe(this) { response ->

            when (response) {
                is NetworkResult.Success -> {
                    hideLoadingDialog()
                    response.data?.let {



                        Toast.makeText(
                            this,
                            "" + response.data.Message,
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(Intent(this,VerifyOtp::class.java).putExtra("mobile",mobile!!).putExtra("type","forget"))

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

}