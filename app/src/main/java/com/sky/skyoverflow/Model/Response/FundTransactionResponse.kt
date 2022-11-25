package com.sky.skyoverflow.Model.Response

import com.google.gson.annotations.SerializedName

data class FundTransactionResponse (

    @SerializedName("StatusCode" ) var StatusCode : String,
    @SerializedName("Message"    ) var Message    : String,
    @SerializedName("Data"       ) var Data       : ArrayList<FundTransaction>

)

data class FundTransaction (

    @SerializedName("TransactionDate" ) var TransactionDate : String,
    @SerializedName("credit"          ) var credit          : String,
    @SerializedName("debit"           ) var debit           : String,
    @SerializedName("reamrk"          ) var reamrk          : String,
    @SerializedName("transtype"       ) var transtype       : String

)