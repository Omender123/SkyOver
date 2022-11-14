package com.sky.skyoverflow.Model.Response

import com.google.gson.annotations.SerializedName

data class CheckReferrelResponse(
    @SerializedName("StatusCode") var StatusCode: String,
    @SerializedName("Message") var Message: String,
    @SerializedName("Data") var checkReferrel: CheckReferrel? = null
)

data class CheckReferrel(

    @SerializedName("sUserID") var sUserID: String? = null,
    @SerializedName("sUserName") var sUserName: String? = null,
    @SerializedName("TotalRechargeBalance") var TotalRechargeBalance: Int? = null

)