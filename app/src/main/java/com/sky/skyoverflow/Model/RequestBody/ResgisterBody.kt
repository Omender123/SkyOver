package com.sky.skyoverflow.Model.RequestBody

import com.google.gson.annotations.SerializedName

data class ResgisterBody(
    @SerializedName("SponserId") var SponserId: String,
    @SerializedName("FirstName") var FirstName: String,
    @SerializedName("LastName") var LastName: String,
    @SerializedName("Mobile") var Mobile: String,
    @SerializedName("stateid") var stateid: String
)
