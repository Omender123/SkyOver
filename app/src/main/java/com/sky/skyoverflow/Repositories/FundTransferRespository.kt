package com.sky.skyoverflow.Repositories

import com.sky.skyoverflow.Model.Response.BaseApiResponse
import com.sky.skyoverflow.Model.Response.CheckReferrelResponse
import com.sky.skyoverflow.Model.Response.CommonRespons
import com.sky.skyoverflow.Utils.NetworkResult
import com.sky.skyoverflow.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject


@ActivityRetainedScoped
class FundTransferRespository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun CheckReferrel(
        userid: String
    ): Flow<NetworkResult<CheckReferrelResponse>> {
        return flow<NetworkResult<CheckReferrelResponse>> {
            emit(safeApiCall {
                remoteDataSource.CheckReferrel(userid)
            })
        }.flowOn(Dispatchers.IO)
    }


    suspend fun MembertoMemberFundTransfer(
        senderUserID: String,
        receiverUserID: String,
        Amount: String
    ): Flow<NetworkResult<CommonRespons>> {
        return flow<NetworkResult<CommonRespons>> {
            emit(safeApiCall {
                remoteDataSource.membertoMemberFundTransfer(senderUserID, receiverUserID, Amount)
            })
        }.flowOn(Dispatchers.IO)
    }
}