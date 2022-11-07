package com.sky.skyoverflow.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sky.skyoverflow.Model.Response.CommonRespons
import com.sky.skyoverflow.Repositories.ForgetPasswordRespository
import com.sky.skyoverflow.Utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
    private val forgetPasswordRespository: ForgetPasswordRespository,
    application: Application
) : AndroidViewModel(application) {


    private val _response: MutableLiveData<NetworkResult<CommonRespons>> = MutableLiveData()
    val ForgetPassword: LiveData<NetworkResult<CommonRespons>> = _response


    fun fetchForgetPasswordResponse(mobile: String) = viewModelScope.launch {

        forgetPasswordRespository.ForgetrPassword(mobile).collect { values ->
            _response.value = values
        }
    }

}
