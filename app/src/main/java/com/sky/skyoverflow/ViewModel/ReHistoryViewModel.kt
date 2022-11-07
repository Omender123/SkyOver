package com.sky.skyoverflow.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sky.skyoverflow.Model.Response.OperatorResponse
import com.sky.skyoverflow.Model.Response.ReHistoryResponse
import com.sky.skyoverflow.Repositories.ReHistoryRespository
import com.sky.skyoverflow.Repositories.RechargeRespository
import com.sky.skyoverflow.Utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReHistoryViewModel @Inject() constructor(
    private val reHistoryRespository: ReHistoryRespository,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableLiveData<NetworkResult<ReHistoryResponse>> = MutableLiveData()
    val GetReHistoryResponse: LiveData<NetworkResult<ReHistoryResponse>> = _response

    fun fetchReHistoryResponse(userId: String) = viewModelScope.launch {
        reHistoryRespository.GetRechargeHistory(userId).collect { values ->
            _response.value = values
        }
    }
}