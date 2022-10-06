package com.sky.skyoverflow.Repositories

import com.sky.skyoverflow.Model.BaseApiResponse
import com.sky.skyoverflow.Model.CheckLoginResponse
import com.sky.skyoverflow.Model.CommonRespons
import com.sky.skyoverflow.Model.StateResponse
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class VerifyOtpRespository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun VerifyMobile(mobile: String, Otp: String): Flow<NetworkResult<CommonRespons>> {
        return flow<NetworkResult<CommonRespons>> {
            emit(safeApiCall { remoteDataSource.VerifyMobile(mobile, Otp) })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun ResendOtp(mobile: String): Flow<NetworkResult<CheckLoginResponse>> {
        return flow<NetworkResult<CheckLoginResponse>> {
            emit(safeApiCall { remoteDataSource.ResendOTP(mobile) })
        }.flowOn(Dispatchers.IO)
    }

}