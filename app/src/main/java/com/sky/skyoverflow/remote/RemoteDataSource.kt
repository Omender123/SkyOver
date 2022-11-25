package com.sky.skyoverflow.remote


import com.sky.skyoverflow.Model.RequestBody.ProfileDetailsUpdateBody
import com.sky.skyoverflow.Model.RequestBody.ResgisterBody
import okhttp3.MultipartBody
import okhttp3.RequestBody
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

    suspend fun GetRechargeHistory(userID: String) =
        apiService.GetRechargeHistory(userID)

    suspend fun GetMemberDetails(userID: String) =
        apiService.GetMemberDetails(userID)

    suspend fun UpdateUserProfile(body: ProfileDetailsUpdateBody) =
        apiService.UpdateUserProfile(body)

    suspend fun ChangePassword(
        mobile: String,
        oldPass: String?,
        newPass: String?,
        confirmPass: String?
    ) = apiService.ChangePassword(mobile, oldPass, newPass, confirmPass)


    suspend fun addFundDeposit(
        userid: RequestBody,
        Amount: RequestBody,
        Paymentmode: RequestBody,
        Remarks: RequestBody?,
        walletType: RequestBody,
        file: MultipartBody.Part

    ) = apiService.AddFundDeposit(userid, Amount, Paymentmode, Remarks, walletType, file)

    suspend fun CheckReferrel(userID: String) =
        apiService.CheckReferrel(userID)


    suspend fun membertoMemberFundTransfer(
        SenderUserID: String,
        ReceiverUserID: String,
        Amount: String
    ) = apiService.membertoMemberFundTransfer(SenderUserID, ReceiverUserID, Amount)

    suspend fun MobileOrDTHRecharge(
        sMobileNumber: String,
        sRechargeType: String,
        sOperatorCode: String,
        sAmount: String,
        sloginid: String
    ) = apiService.MobileOrDTHRecharge(
        sMobileNumber,
        sRechargeType,
        sOperatorCode,
        sAmount,
        sloginid
    )

    suspend fun GetFundUploadHistory(userID: String) =
        apiService.GetFundUploadHistory(userID)

    suspend fun GetTransactionHistory(userID: String) =
        apiService.GetTransactionHistory(userID)
}


