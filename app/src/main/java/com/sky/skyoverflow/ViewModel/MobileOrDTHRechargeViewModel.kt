package com.sky.skyoverflow.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sky.skyoverflow.Model.Response.CommonRespons
import com.sky.skyoverflow.Repositories.AddFundRespository
import com.sky.skyoverflow.Repositories.MobileOrDTHRechargeRespository
import com.sky.skyoverflow.Utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject


@HiltViewModel
class MobileOrDTHRechargeViewModel @Inject constructor
    (
    private val mobileOrDTHRechargeRespository: MobileOrDTHRechargeRespository,
    application: Application
) : AndroidViewModel(application) {


    private val _response: MutableLiveData<NetworkResult<CommonRespons>> = MutableLiveData()
    val response: LiveData<NetworkResult<CommonRespons>> = _response


    fun MobileOrDTHRechargeRequest(
        sMobileNumber: String,
        sRechargeType: String,
        sOperatorCode: String,
        sAmount: String,
        sloginid: String
    ) = viewModelScope.launch {

        mobileOrDTHRechargeRespository.MobileOrDTHRecharge(
            sMobileNumber,
            sRechargeType,
            sOperatorCode,
            sAmount,
            sloginid
        )
            .collect { values ->
                _response.value = values
            }
    }


}