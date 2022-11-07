package com.sky.skyoverflow.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sky.skyoverflow.Model.RequestBody.ResgisterBody
import com.sky.skyoverflow.Model.Response.StateResponse
import com.sky.skyoverflow.Repositories.ResgisterRepository
import com.sky.skyoverflow.Utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject


@HiltViewModel
class ResgisterViewModel @Inject constructor
    (
    private val loginRepository: ResgisterRepository,
    application: Application
) : AndroidViewModel(application) {


    private val getStateResponse: MutableLiveData<NetworkResult<StateResponse>> = MutableLiveData()
    val getState: LiveData<NetworkResult<StateResponse>> = getStateResponse

    private val getResgiterResponse: MutableLiveData<NetworkResult<ResponseBody>> = MutableLiveData()
    val ResgisterMember: LiveData<NetworkResult<ResponseBody>> = getResgiterResponse


    fun fetchStateResponse() = viewModelScope.launch {

        loginRepository.getState().collect { values ->
            getStateResponse.value = values
        }
    }

    fun fetchResgisterResponse(body: ResgisterBody) = viewModelScope.launch {

        loginRepository.ResgisterMember(body).collect { values ->
            getResgiterResponse.value = values
        }
    }
}