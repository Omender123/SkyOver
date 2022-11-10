package com.sky.skyoverflow.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sky.skyoverflow.Model.RequestBody.ProfileDetailsUpdateBody
import com.sky.skyoverflow.Model.Response.CommonRespons
import com.sky.skyoverflow.Model.Response.ProfileDetailsResponse
import com.sky.skyoverflow.Repositories.ProfileDetailsRespository
import com.sky.skyoverflow.Repositories.ProfileUpdateRespository
import com.sky.skyoverflow.Utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileUpdateViewModel @Inject constructor
    (
    private val updateRespository: ProfileUpdateRespository,
    application: Application
) : AndroidViewModel(application) {


    private val _response: MutableLiveData<NetworkResult<CommonRespons>> = MutableLiveData()
    val response: LiveData<NetworkResult<CommonRespons>> = _response


    fun fetchUpdateProfileDetailsResponse(body: ProfileDetailsUpdateBody) = viewModelScope.launch {

        updateRespository.ProfileUpdate(body).collect { values ->
            _response.value = values
        }
    }


}