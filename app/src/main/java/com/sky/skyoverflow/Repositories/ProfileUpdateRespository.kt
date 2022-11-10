package com.sky.skyoverflow.Repositories

import com.sky.skyoverflow.Model.RequestBody.ProfileDetailsUpdateBody
import com.sky.skyoverflow.Model.Response.BaseApiResponse
import com.sky.skyoverflow.Model.Response.CommonRespons
import com.sky.skyoverflow.Model.Response.ProfileDetailsResponse
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


@ActivityRetainedScoped
class ProfileUpdateRespository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse(){

    suspend fun ProfileUpdate(body: ProfileDetailsUpdateBody): Flow<NetworkResult<CommonRespons>> {
        return flow<NetworkResult<CommonRespons>> {
            emit(safeApiCall { remoteDataSource.UpdateUserProfile(body) })
        }.flowOn(Dispatchers.IO)
    }
}