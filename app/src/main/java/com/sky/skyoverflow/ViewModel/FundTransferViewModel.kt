package com.sky.skyoverflow.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sky.skyoverflow.Model.Response.CheckReferrelResponse
import com.sky.skyoverflow.Model.Response.CommonRespons
import com.sky.skyoverflow.Repositories.FundTransferRespository
import com.sky.skyoverflow.Utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FundTransferViewModel @Inject() constructor(
    private val fundTransferRespository: FundTransferRespository,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableLiveData<NetworkResult<CheckReferrelResponse>> = MutableLiveData()
    val CheckReferrel: LiveData<NetworkResult<CheckReferrelResponse>> = _response


    fun CheckReferrelRequest(userid: String) = viewModelScope.launch {
        fundTransferRespository.CheckReferrel(userid).collect { values ->
            _response.value = values
        }
    }

    private val FundTransfer_response: MutableLiveData<NetworkResult<CommonRespons>> =
        MutableLiveData()
    val GetFundTransferResponse: LiveData<NetworkResult<CommonRespons>> = FundTransfer_response

    fun FundTransferRequest(senderUserid: String, receiverUserfID: String, Amount: String) =
        viewModelScope.launch {
            fundTransferRespository.MembertoMemberFundTransfer(senderUserid,receiverUserfID,Amount).collect { values ->
                FundTransfer_response.value = values
            }
        }

}