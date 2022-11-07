package com.sky.skyoverflow.Model.Response

import com.google.gson.annotations.SerializedName

data class OperatorResponse(
    @SerializedName("StatusCode") var StatusCode: String,
    @SerializedName("Message") var Message: String,
    @SerializedName("Data") var Data: ArrayList<Operator>
)


data class Operator (

    @SerializedName("OperatorName" ) var OperatorName : String,
    @SerializedName("OpCode"       ) var OpCode       : String,
    @SerializedName("logoimg"      ) var logoimg      : String

)