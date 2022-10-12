package com.sky.skyoverflow.Repositories

import com.sky.skyoverflow.Model.BaseApiResponse
import com.sky.skyoverflow.Model.CheckLoginResponse
import com.sky.skyoverflow.Model.DashboradResponse
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

@ActivityRetainedScoped
class DesboardRespository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    BaseApiResponse() {

    suspend fun getGetDashboard(userID:String): Flow<NetworkResult<DashboradResponse>> {
        return flow<NetworkResult<DashboradResponse>> {
            emit(safeApiCall { remoteDataSource.GetDashBoard(userID) })
        }.flowOn(Dispatchers.IO)
    }

}