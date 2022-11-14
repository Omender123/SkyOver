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
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject


@ActivityRetainedScoped
class AddFundRespository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    suspend fun AddFundDeposit(
        userid: RequestBody,
        Amount: RequestBody,
        Paymentmode: RequestBody,
        Remarks: RequestBody?,
        walletType: RequestBody,
        file: MultipartBody.Part
    ): Flow<NetworkResult<CommonRespons>> {
        return flow<NetworkResult<CommonRespons>> {
            emit(safeApiCall {
                remoteDataSource.addFundDeposit(userid, Amount, Paymentmode, Remarks, walletType, file)
            })
        }.flowOn(Dispatchers.IO)
    }

}