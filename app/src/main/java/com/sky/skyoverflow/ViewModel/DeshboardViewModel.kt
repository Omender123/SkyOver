package com.sky.skyoverflow.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sky.skyoverflow.Model.CheckLoginResponse
import com.sky.skyoverflow.Model.DashboradResponse
import com.sky.skyoverflow.Repositories.DesboardRespository
import com.sky.skyoverflow.Utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeshboardViewModel @Inject() constructor(
    private val desboardRespository: DesboardRespository,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableLiveData<NetworkResult<DashboradResponse>> = MutableLiveData()
    val GetDeshBoardResponse: LiveData<NetworkResult<DashboradResponse>> = _response

    fun fetchDeshBoardResponse(userid: String) = viewModelScope.launch {
        desboardRespository.getGetDashboard(userid).collect { values ->
            _response.value = values
        }
    }

}