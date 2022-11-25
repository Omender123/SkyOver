package com.sky.skyoverflow.remote


import com.sky.skyoverflow.Model.RequestBody.ProfileDetailsUpdateBody
import com.sky.skyoverflow.Model.RequestBody.ResgisterBody
import com.sky.skyoverflow.Model.Response.*
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("Authentication/CheckLogin/{username}/{password}")
    suspend fun getCheckLogin(
        @Path("username") username: String,
        @Path("password") password: String
    ): Response<CheckLoginResponse>

    @GET("Authentication/getState")
    suspend fun getState(): Response<StateResponse>

    @POST("Authentication/create")
    suspend fun ResgisterMember(
        @Body body: ResgisterBody
    ): Response<ResponseBody>

    @FormUrlEncoded
    @POST("Authentication/verifyMobile")
    suspend fun VerifyMobile(
        @Field("mobile") mobile: String,
        @Field("otpcode") otp: String
    ): Response<CommonRespons>


    @FormUrlEncoded
    @POST("Authentication/ResendOTP")
    suspend fun ResendOTP(
        @Field("Mobile") mobile: String
    ): Response<CheckLoginResponse>


    @GET("Authentication/ForgotPassword/{Mobile}")
    suspend fun ForgotPassword(
        @Path("Mobile") Mobile: String
    ): Response<CommonRespons>

    @GET("Authentication/ResendForgetOTP/{Mobile}")
    suspend fun ResendForgetOTP(
        @Path("Mobile") Mobile: String
    ): Response<CommonRespons>


    @FormUrlEncoded
    @POST("Authentication/verifyForgetPasswordOTP")
    suspend fun verifyForgetPasswordOTP(
        @Field("Mobile") mobile: String,
        @Field("OtpCode") otp: String
    ): Response<CommonRespons>

    @GET("Authentication/GetDashBoard/{userID}")
    suspend fun GetDashBoard(
        @Path("userID") userID: String
    ): Response<DashboradResponse>

    @GET("Authentication/GetOperator/{operator}")
    suspend fun GetOperator(
        @Path("operator") OperatorType: String
    ): Response<OperatorResponse>

    @GET("Authentication/GetRechargeHistory/{userID}")
    suspend fun GetRechargeHistory(
        @Path("userID") userID: String
    ): Response<ReHistoryResponse>

    @GET("Authentication/MemberDetails/{userID}")
    suspend fun GetMemberDetails(
        @Path("userID") userID: String
    ): Response<ProfileDetailsResponse>

    @POST("Authentication/UpdateUserProfile")
    suspend fun UpdateUserProfile(
        @Body body: ProfileDetailsUpdateBody
    ): Response<CommonRespons>

    @FormUrlEncoded
    @POST("Authentication/ChangePassword")
    suspend fun ChangePassword(
        @Field("Mobile") Mobile: String,
        @Field("OldPassword") OldPassword: String?,
        @Field("NewPassword") NewPassword: String?,
        @Field("ConfirmPassword") ConfirmPassword: String?
    ): Response<CommonRespons>

    @Multipart
    @POST("Authentication/FundDeposit")
    suspend fun AddFundDeposit(
        @Part("userid") userid: RequestBody,
        @Part("Amount") Amount: RequestBody,
        @Part("Paymentmode") Paymentmode: RequestBody,
        @Part("Remarks") Remarks: RequestBody?,
        @Part("walletType") walletType: RequestBody,
        @Part file: MultipartBody.Part
    ): Response<CommonRespons>


    @GET("Authentication/CheckReferrel/{userID}")
    suspend fun CheckReferrel(
        @Path("userID") userID: String
    ): Response<CheckReferrelResponse>

    @FormUrlEncoded
    @POST("Authentication/membertoMemberFundTransfer")
    suspend fun membertoMemberFundTransfer(
        @Field("SenderUserID") SenderUserID: String,
        @Field("ReceiverUserID") ReceiverUserID: String,
        @Field("Amount") Amount: String
    ): Response<CommonRespons>


    @FormUrlEncoded
    @POST("Authentication/Recharge")
    suspend fun MobileOrDTHRecharge(
        @Field("sMobileNumber") sMobileNumber: String,
        @Field("sRechargeType") sRechargeType: String,
        @Field("sOperatorCode") sOperatorCode: String,
        @Field("sAmount") sAmount: String,
        @Field("sloginid") sloginid: String
    ): Response<CommonRespons>

    @GET("Authentication/GetFundUploadHistory/{userID}")
    suspend fun GetFundUploadHistory(
        @Path("userID") userID: String
    ): Response<AddFundHistoryResponse>

    @FormUrlEncoded
    @POST("Authentication/GetTransactionHistory")
    suspend fun GetTransactionHistory(
        @Field("suserID") suserID: String
    ): Response<FundTransactionResponse>

}

