package com.sky.skyoverflow.remote


import com.sky.skyoverflow.Model.RequestBody.ResgisterBody
import com.sky.skyoverflow.Model.Response.*
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

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

}

