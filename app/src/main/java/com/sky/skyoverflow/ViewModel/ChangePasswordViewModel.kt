package com.sky.skyoverflow.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.sky.skyoverflow.Model.Response.CommonRespons
import com.sky.skyoverflow.Repositories.ChangePasswordRespository
import com.sky.skyoverflow.Repositories.ForgetPasswordRespository
import com.sky.skyoverflow.Utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val changePasswordRespository: ChangePasswordRespository,
    application: Application
) : AndroidViewModel(application) {


    private val _response: MutableLiveData<NetworkResult<CommonRespons>> = MutableLiveData()
    val ChangePasswordResponse: LiveData<NetworkResult<CommonRespons>> = _response


    fun ChangePassword(
        mobile: String,
        oldPass: String?,
        newPass: String?,
        confirmPass: String?
    ) = viewModelScope.launch {
        changePasswordRespository.ChangePassword(mobile, oldPass, newPass, confirmPass)
            .collect { values ->

                _response.value = values
            }

    }

}