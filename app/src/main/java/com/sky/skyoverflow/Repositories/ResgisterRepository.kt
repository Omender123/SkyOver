package com.sky.skyoverflow.Repositories

import com.sky.skyoverflow.Model.Response.BaseApiResponse
import com.sky.skyoverflow.Model.RequestBody.ResgisterBody
import com.sky.skyoverflow.Model.Response.StateResponse
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody
import javax.inject.Inject

@ActivityRetainedScoped
class ResgisterRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun getState(): Flow<NetworkResult<StateResponse>> {
        return flow<NetworkResult<StateResponse>> {
            emit(safeApiCall { remoteDataSource.getState() })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun ResgisterMember(body: ResgisterBody): Flow<NetworkResult<ResponseBody>> {
        return flow<NetworkResult<ResponseBody>> {
            emit(safeApiCall { remoteDataSource.Register_Member(body) })
        }.flowOn(Dispatchers.IO)
    }

}