package com.sky.skyoverflow.Repositories

import com.sky.skyoverflow.Model.Response.BaseApiResponse
import com.sky.skyoverflow.Model.Response.OperatorResponse
import com.sky.skyoverflow.Model.Response.ReHistory
import com.sky.skyoverflow.Model.Response.ReHistoryResponse
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class ReHistoryRespository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    BaseApiResponse() {

    suspend fun GetRechargeHistory(userID: String): Flow<NetworkResult<ReHistoryResponse>> {
        return flow<NetworkResult<ReHistoryResponse>> {
            emit(safeApiCall { remoteDataSource.GetRechargeHistory(userID) })
        }.flowOn(Dispatchers.IO)
    }

}