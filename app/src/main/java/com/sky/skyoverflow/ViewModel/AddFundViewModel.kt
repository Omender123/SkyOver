package com.sky.skyoverflow.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sky.skyoverflow.Model.Response.CheckLoginResponse
import com.sky.skyoverflow.Model.Response.CommonRespons
import com.sky.skyoverflow.Repositories.AddFundRespository
import com.sky.skyoverflow.Repositories.LoginRepository
import com.sky.skyoverflow.Utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject


@HiltViewModel
class AddFundViewModel @Inject constructor
    (
    private val addFundRespository: AddFundRespository,
    application: Application
) : AndroidViewModel(application) {


    private val _response: MutableLiveData<NetworkResult<CommonRespons>> = MutableLiveData()
    val response: LiveData<NetworkResult<CommonRespons>> = _response


    fun addFundRequest(
        userid: RequestBody,
        Amount: RequestBody,
        Paymentmode: RequestBody,
        Remarks: RequestBody?,
        walletType: RequestBody,
        file: MultipartBody.Part
    ) = viewModelScope.launch {

        addFundRespository.AddFundDeposit(userid, Amount, Paymentmode, Remarks, walletType, file)
            .collect { values ->
                _response.value = values
            }
    }


}