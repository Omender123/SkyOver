package com.sky.skyoverflow.Model.Response

import com.google.gson.annotations.SerializedName


data class AddFundHistoryResponse(

    @SerializedName("StatusCode") var StatusCode: String,
    @SerializedName("Message") var Message: String,
    @SerializedName("Data") var Data: ArrayList<AddFundHistory>

)

data class AddFundHistory(

    @SerializedName("kitdesc") var kitdesc: String,
    @SerializedName("LoginID") var LoginID: String,
    @SerializedName("Bank") var Bank: String,
    @SerializedName("PaymentMode") var PaymentMode: String,
    @SerializedName("PaymentDate") var PaymentDate: String,
    @SerializedName("Commentbox") var Commentbox: String,
    @SerializedName("Ramount") var Ramount: String,
    @SerializedName("walletType") var walletType: String,
    @SerializedName("Rf_Status") var RfStatus: String,
    @SerializedName("refrenceNo") var refrenceNo: String

)