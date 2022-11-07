package com.sky.skyoverflow.Repositories

import com.sky.skyoverflow.Model.Response.BaseApiResponse
import com.sky.skyoverflow.Model.Response.CommonRespons
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class ForgetPasswordRespository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun ForgetrPassword(Mobile:String): Flow<NetworkResult<CommonRespons>> {
        return flow<NetworkResult<CommonRespons>> {
            emit(safeApiCall { remoteDataSource.ForgetPassword(Mobile) })
        }.flowOn(Dispatchers.IO)
    }

}
