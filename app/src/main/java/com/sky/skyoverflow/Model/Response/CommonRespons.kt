package com.sky.skyoverflow.Model.Response

import com.google.gson.annotations.SerializedName


data class CommonRespons(
    @SerializedName("StatusCode") var StatusCode: String,
    @SerializedName("Message") var Message: String,
    @SerializedName("Data") var Data :String
)