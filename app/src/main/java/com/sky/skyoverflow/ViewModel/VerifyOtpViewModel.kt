package com.sky.skyoverflow.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sky.skyoverflow.Model.CheckLoginResponse
import com.sky.skyoverflow.Model.CommonRespons
import com.sky.skyoverflow.Model.RequestBody.ResgisterBody
import com.sky.skyoverflow.Model.StateResponse
import com.sky.skyoverflow.Repositories.ResgisterRepository
import com.sky.skyoverflow.Repositories.VerifyOtpRespository
import com.sky.skyoverflow.Utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class VerifyOtpViewModel @Inject constructor
    (
    private val verifyOtpRespository: VerifyOtpRespository,
    application: Application
) : AndroidViewModel(application) {


    private val getVerifyOtpResponse: MutableLiveData<NetworkResult<CommonRespons>> =
        MutableLiveData()
    val getVerifyOtp: LiveData<NetworkResult<CommonRespons>> = getVerifyOtpResponse

    private val getResendOtpResponse: MutableLiveData<NetworkResult<CheckLoginResponse>> =
        MutableLiveData()
    val getResendOtp: LiveData<NetworkResult<CheckLoginResponse>> = getResendOtpResponse


    private val getverifyForgetPasswordOTPResponse: MutableLiveData<NetworkResult<CommonRespons>> =
        MutableLiveData()
    val getVerifyForgetPasswordOTP: LiveData<NetworkResult<CommonRespons>> =
        getverifyForgetPasswordOTPResponse

    private val getResendForgetOTPResponse: MutableLiveData<NetworkResult<CommonRespons>> =
        MutableLiveData()
    val getResendForgetOTP: LiveData<NetworkResult<CommonRespons>> = getResendForgetOTPResponse


    fun fetchVerifyMobileResponse(mobile: String, Otp: String) = viewModelScope.launch {
        verifyOtpRespository.VerifyMobile(mobile, Otp).collect { values ->
            getVerifyOtpResponse.value = values
        }
    }

    fun fetchResendOtpResponse(mobile: String) = viewModelScope.launch {

        verifyOtpRespository.ResendOtp(mobile).collect { values ->
            getResendOtpResponse.value = values
        }
    }


    fun fetchverifyForgetPasswordOTPResponse(mobile: String, Otp: String) = viewModelScope.launch {
        verifyOtpRespository.verifyForgetPasswordOTP(mobile, Otp).collect { values ->
            getverifyForgetPasswordOTPResponse.value = values
        }
    }

    fun fetchResendForgetOTPResponse(mobile: String) = viewModelScope.launch {

        verifyOtpRespository.ResendForgetOTP(mobile).collect { values ->
            getResendForgetOTPResponse.value = values
        }
    }
}