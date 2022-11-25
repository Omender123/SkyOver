package com.sky.skyoverflow.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sky.skyoverflow.Model.Response.AddFundHistoryResponse
import com.sky.skyoverflow.Model.Response.CommonRespons
import com.sky.skyoverflow.Model.Response.FundTransactionResponse
import com.sky.skyoverflow.Repositories.AddFundRespository
import com.sky.skyoverflow.Repositories.FundHistoryRespository
import com.sky.skyoverflow.Utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class FundHistoryViewModel @Inject constructor
    (
    private val fundHistoryRespository: FundHistoryRespository,
    application: Application
) : AndroidViewModel(application) {


    private val _response: MutableLiveData<NetworkResult<AddFundHistoryResponse>> =
        MutableLiveData()
    val response: LiveData<NetworkResult<AddFundHistoryResponse>> = _response

    private val fund_response: MutableLiveData<NetworkResult<FundTransactionResponse>> =
        MutableLiveData()
    val GetTransactionHistoryResponse: LiveData<NetworkResult<FundTransactionResponse>> = fund_response


    fun SendFundAddHistoryRequest(
        userid: String
    ) = viewModelScope.launch {

        fundHistoryRespository.GetFundUploadHistory(userid)
            .collect { values ->
                _response.value = values
            }
    }

    fun GetTransactionHistoryResponse(
        userid: String
    ) = viewModelScope.launch {

        fundHistoryRespository.GetTransactionHistory(userid)
            .collect { values ->
                fund_response.value = values
            }
    }

}