package com.sky.skyoverflow.Model.RequestBody

import com.google.gson.annotations.SerializedName

data class ProfileDetailsUpdateBody(
    @SerializedName("FirstName") var FirstName: String,
    @SerializedName("LastName") var LastName: String? = null,
    @SerializedName("Mobile") var Mobile: String,
    @SerializedName("FatherHusbandName") var FatherHusbandName: String? = null,
    @SerializedName("DOB") var DOB: String? = null,
    @SerializedName("PanNo") var PanNo: String? = null,
    @SerializedName("Email") var Email: String? = null,
    @SerializedName("Gender") var Gender: String? = null,
    @SerializedName("BankName") var BankName: String? = null,
    @SerializedName("PayeeName") var PayeeName: String? = null,
    @SerializedName("AccountNo") var AccountNo: String? = null,
    @SerializedName("IfscCode") var IfscCode: String? = null,
    @SerializedName("Branch") var Branch: String? = null
)

