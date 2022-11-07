package com.sky.skyoverflow.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sky.skyoverflow.Model.Response.DashboradResponse
import com.sky.skyoverflow.Model.Response.OperatorResponse
import com.sky.skyoverflow.Repositories.DesboardRespository
import com.sky.skyoverflow.Repositories.RechargeRespository
import com.sky.skyoverflow.Utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RechargeViewModel @Inject() constructor(
    private val rechargeRespository: RechargeRespository,
    application: Application
) : AndroidViewModel(application)  {

    private val _response: MutableLiveData<NetworkResult<OperatorResponse>> = MutableLiveData()
    val GetOperatorResponse: LiveData<NetworkResult<OperatorResponse>> = _response

    fun fetchOperatorResponse(OperatorType: String) = viewModelScope.launch {
        rechargeRespository.GetOperator(OperatorType).collect { values ->
            _response.value = values
        }
    }

}