package com.sky.skyoverflow.Repositories

import com.sky.skyoverflow.Model.Response.BaseApiResponse
import com.sky.skyoverflow.Model.Response.CheckLoginResponse
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
class ChangePasswordRespository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun ChangePassword(
        mobile: String,
        OldPassword: String?,
        NewPassword: String?,
        confirmPassword: String?
    ): Flow<NetworkResult<CommonRespons>> {
        return flow<NetworkResult<CommonRespons>> {
            emit(safeApiCall {
                remoteDataSource.ChangePassword(
                    mobile,
                    OldPassword,
                    NewPassword,
                    confirmPassword
                )
            })
        }.flowOn(Dispatchers.IO)
    }

}