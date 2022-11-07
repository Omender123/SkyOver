package com.sky.skyoverflow.Model.Response

import com.google.gson.annotations.SerializedName

data class StateResponse(
    @SerializedName("StatusCode") var StatusCode: String,
    @SerializedName("Message") var Message: String,
    @SerializedName("Data") var Data: ArrayList<Data1>
)

data class Data1(

    @SerializedName("Stateid") var Stateid: Int,
    @SerializedName("StateName") var StateName: String

)