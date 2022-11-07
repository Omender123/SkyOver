package com.sky.skyoverflow.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sky.skyoverflow.Model.Response.CheckLoginResponse
import com.sky.skyoverflow.Repositories.LoginRepository
import com.sky.skyoverflow.Utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor
    (
    private val loginRepository: LoginRepository,
    application: Application
) : AndroidViewModel(application) {


    private val _response: MutableLiveData<NetworkResult<CheckLoginResponse>> = MutableLiveData()
    val response: LiveData<NetworkResult<CheckLoginResponse>> = _response


    fun fetchCheckLoginResponse(username:String,password:String) = viewModelScope.launch {

        loginRepository.getCheckLogin(username, password).collect { values ->
            _response.value = values
        }
    }


}