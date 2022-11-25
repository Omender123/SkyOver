package com.sky.skyoverflow.Repositories

import com.sky.skyoverflow.Model.Response.AddFundHistoryResponse
import com.sky.skyoverflow.Model.Response.BaseApiResponse
import com.sky.skyoverflow.Model.Response.CommonRespons
import com.sky.skyoverflow.Model.Response.FundTransactionResponse
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
class FundHistoryRespository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun GetFundUploadHistory(
        userid: String
    ): Flow<NetworkResult<AddFundHistoryResponse>> {
        return flow<NetworkResult<AddFundHistoryResponse>> {
            emit(safeApiCall {
                remoteDataSource.GetFundUploadHistory(userid)
            })
        }.flowOn(Dispatchers.IO)
    }


    suspend fun GetTransactionHistory(
        userid: String
    ): Flow<NetworkResult<FundTransactionResponse>> {
        return flow<NetworkResult<FundTransactionResponse>> {
            emit(safeApiCall {
                remoteDataSource.GetTransactionHistory(userid)
            })
        }.flowOn(Dispatchers.IO)
    }


}