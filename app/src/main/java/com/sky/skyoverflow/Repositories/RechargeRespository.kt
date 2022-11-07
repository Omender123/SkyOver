package com.sky.skyoverflow.Repositories

import com.sky.skyoverflow.Model.Response.BaseApiResponse
import com.sky.skyoverflow.Model.Response.DashboradResponse
import com.sky.skyoverflow.Model.Response.OperatorResponse
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class RechargeRespository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    BaseApiResponse() {

    suspend fun GetOperator(OperatorType:String): Flow<NetworkResult<OperatorResponse>> {
        return flow<NetworkResult<OperatorResponse>> {
            emit(safeApiCall { remoteDataSource.GetOperator(OperatorType) })
        }.flowOn(Dispatchers.IO)
    }

}