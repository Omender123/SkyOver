package com.sky.skyoverflow.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sky.skyoverflow.Model.Response.CheckLoginResponse
import com.sky.skyoverflow.Model.Response.ProfileDetailsResponse
import com.sky.skyoverflow.Repositories.LoginRepository
import com.sky.skyoverflow.Repositories.ProfileDetailsRespository
import com.sky.skyoverflow.Utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileDetailsViewModel @Inject constructor
    (
    private val profileDetailsRespository: ProfileDetailsRespository,
    application: Application
) : AndroidViewModel(application) {


    private val _response: MutableLiveData<NetworkResult<ProfileDetailsResponse>> = MutableLiveData()
    val response: LiveData<NetworkResult<ProfileDetailsResponse>> = _response


    fun fetchProfileDetailsResponse(userID:String) = viewModelScope.launch {

        profileDetailsRespository.getProfileDetails(userID).collect { values ->
            _response.value = values
        }
    }


}