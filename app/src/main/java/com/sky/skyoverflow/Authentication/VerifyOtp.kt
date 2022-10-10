package com.sky.skyoverflow.Authentication

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.sky.skyoverflow.R
import com.sky.skyoverflow.Utils.LoadingDialog
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.ViewModel.ResgisterViewModel
import com.sky.skyoverflow.ViewModel.VerifyOtpViewModel
import com.sky.skyoverflow.databinding.ActivityLoginBinding
import com.sky.skyoverflow.databinding.ActivityVerifyOtpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyOtp : AppCompatActivity(), View.OnClickListener {
    lateinit var loadingDialog: LoadingDialog
    private lateinit var binding: ActivityVerifyOtpBinding
    private val verifyOtpViewModel: VerifyOtpViewModel by viewModels()
    var mobile: String? = null
    var type: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadingDialog = LoadingDialog(this)
        var bundle: Bundle? = intent.extras
        if (bundle != null) {
            mobile = bundle.getString("mobile")
            type = bundle.getString("type")
            Log.e("mobile", mobile!!)
            Log.e("type", type!!)
        }

        binding.txtResend.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
        binding.imgBack.setOnClickListener(this)
        VerifyObserval()
    }

    private fun VerifyObserval() {
        verifyOtpViewModel.getResendOtp.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideLoadingDialog()
                    response.data?.let {
                        Toast.makeText(
                            this,
                            "" + it.Message,
                            Toast.LENGTH_SHORT
                        ).show()

                        Log.e("Error", it.Message.toString())
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
        verifyOtpViewModel.getResendForgetOTP.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideLoadingDialog()
                    response.data?.let {

                        Log.e("Error", it.Message.toString())

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

        verifyOtpViewModel.getVerifyOtp.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideLoadingDialog()
                    response.data?.let {
                        binding.btnSubmit.visibility = View.GONE
                        binding.enterOtp.visibility = View.GONE
                        binding.txtResend.visibility = View.GONE
                        binding.txt.visibility = View.GONE
                        binding.txtShowDetails.setText(it.Message)
                        binding.txtShowDetails.visibility = View.VISIBLE
                        binding.btnLogin.visibility = View.VISIBLE
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
        verifyOtpViewModel.getVerifyForgetPasswordOTP.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideLoadingDialog()
                    response.data?.let {
                        Toast.makeText(
                            this,
                            it.Message,
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this, Login::class.java))
                        finish()

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

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.txt_resend -> {
                if (type.equals("resister", true)) {
                    showLoadingDialog()
                    verifyOtpViewModel.fetchResendOtpResponse(mobile!!)
                } else if (type.equals("forget", true)) {
                    showLoadingDialog()
                    verifyOtpViewModel.fetchResendForgetOTPResponse(mobile!!)
                }

            }

            R.id.btn_submit -> {
                if (binding.enterOtp.text.toString().trim().isEmpty()) {
                    binding.enterOtp.requestFocus()
                    Toast.makeText(this, "Please enter OTP", Toast.LENGTH_SHORT).show()
                } else {
                    if (type.equals("resister", true)) {
                        showLoadingDialog()
                        verifyOtpViewModel.fetchVerifyMobileResponse(
                            mobile!!,
                            binding.enterOtp.text.toString().trim()
                        )
                    } else if (type.equals("forget", true)) {
                        showLoadingDialog()
                        verifyOtpViewModel.fetchverifyForgetPasswordOTPResponse(
                            mobile!!,
                            binding.enterOtp.text.toString().trim()
                        )
                    }

                }

            }

            R.id.btn_login -> {
                startActivity(Intent(this, Login::class.java))
                finish()
            }
            R.id.img_back -> {
                onBackPressed()
            }
        }
    }
}