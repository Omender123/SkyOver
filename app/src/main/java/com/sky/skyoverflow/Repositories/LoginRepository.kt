package com.sky.skyoverflow.Repositories

import com.sky.skyoverflow.Model.Response.BaseApiResponse
import com.sky.skyoverflow.Model.Response.CheckLoginResponse
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@ActivityRetainedScoped
class LoginRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun getCheckLogin(username:String,password:String): Flow<NetworkResult<CheckLoginResponse>> {
        return flow<NetworkResult<CheckLoginResponse>> {
            emit(safeApiCall { remoteDataSource.getCheckLogin(username, password) })
        }.flowOn(Dispatchers.IO)
    }

}
