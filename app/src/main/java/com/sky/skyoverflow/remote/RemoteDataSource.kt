package com.sky.skyoverflow.remote


import com.sky.skyoverflow.Model.RequestBody.ResgisterBody
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getCheckLogin(username: String, password: String) =
        apiService.getCheckLogin(username, password)

    suspend fun getState() =
        apiService.getState()

    suspend fun Register_Member(body: ResgisterBody) =
        apiService.ResgisterMember(body)

    suspend fun VerifyMobile(mobile: String, otp: String) =
        apiService.VerifyMobile(mobile, otp)

    suspend fun ResendOTP(mobile: String) =
        apiService.ResendOTP(mobile)

    suspend fun ForgetPassword(mobile: String) =
        apiService.ForgotPassword(mobile)

    suspend fun verifyForgetPasswordOTP(mobile: String, otp: String) =
        apiService.verifyForgetPasswordOTP(mobile, otp)

    suspend fun ResendForgetOTP(mobile: String) =
        apiService.ResendForgetOTP(mobile)

    suspend fun GetDashBoard(userID: String) =
        apiService.GetDashBoard(userID)

    suspend fun GetOperator(OperatorType: String) =
        apiService.GetOperator(OperatorType)

}