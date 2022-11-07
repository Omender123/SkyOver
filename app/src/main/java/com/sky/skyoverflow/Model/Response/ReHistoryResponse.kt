package com.sky.skyoverflow.Model.Response

import com.google.gson.annotations.SerializedName

data class ReHistoryResponse(
    @SerializedName("StatusCode") var StatusCode: String,
    @SerializedName("Message") var Message: String,
    @SerializedName("Data") var Data: ArrayList<ReHistory>
)


data class ReHistory (

    @SerializedName("regno"         ) var regno         : Int,
    @SerializedName("smobile"       ) var smobile       : String,
    @SerializedName("amount"        ) var amount        : Int,
    @SerializedName("sRDate"        ) var sRDate        : String,
    @SerializedName("sStatus"       ) var sStatus       : String,
    @SerializedName("sOperatorName" ) var sOperatorName : String,
    @SerializedName("sOtype"        ) var sOtype        : String,
    @SerializedName("sPayMode"      ) var sPayMode      : String,
    @SerializedName("credit"        ) var credit        : String,
    @SerializedName("debit"         ) var debit         : String,
    @SerializedName("transType"     ) var transType     : String,
    @SerializedName("Remark"        ) var Remark        : String,
    @SerializedName("Transid"       ) var Transid       : String

)