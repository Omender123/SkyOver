package com.sky.skyoverflow.Model

import com.google.gson.annotations.SerializedName

data class DashboradResponse(
    @SerializedName("StatusCode") var StatusCode: String,
    @SerializedName("Message") var Message: String,
    @SerializedName("Data") var Data: ArrayList<Dashborad>
)
data class Dashborad (

    @SerializedName("MainwalletBallance"       ) var MainwalletBallance       : String,
    @SerializedName("topupWalletBalance"       ) var topupWalletBalance       : String,
    @SerializedName("slefCashBackWallet"       ) var slefCashBackWallet       : String,
    @SerializedName("CardWallet"               ) var CardWallet               : String,
    @SerializedName("LevelIncome"              ) var LevelIncome              : String,
    @SerializedName("singleLegIncome"          ) var singleLegIncome          : String,
    @SerializedName("TotalIncome"              ) var TotalIncome              : String,
    @SerializedName("AllsingleLegMember"       ) var AllsingleLegMember       : String,
    @SerializedName("singleLegMember"          ) var singleLegMember          : String,
    @SerializedName("singleLegActiveMember"    ) var singleLegActiveMember    : String,
    @SerializedName("singleLegInActiveMember"  ) var singleLegInActiveMember  : String,
    @SerializedName("tottopup"                 ) var tottopup                 : String,
    @SerializedName("capping"                  ) var capping                  : String,
    @SerializedName("directMem"                ) var directMem                : String,
    @SerializedName("ActiveDirectMem"          ) var ActiveDirectMem          : String,
    @SerializedName("InActiveDirectMem"        ) var InActiveDirectMem        : String,
    @SerializedName("loginid"                  ) var loginid                  : String,
    @SerializedName("name"                     ) var name                     : String,
    @SerializedName("mobile"                   ) var mobile                   : String,
    @SerializedName("emailid"                  ) var emailid                  : String,
    @SerializedName("Tendigitmobile"           ) var Tendigitmobile           : String,
    @SerializedName("Sponsor"                  ) var Sponsor                  : String,
    @SerializedName("uPhoto"                   ) var uPhoto                   : String,
    @SerializedName("RegDate"                  ) var RegDate                  : String,
    @SerializedName("topupdate"                ) var topupdate                : String,
    @SerializedName("UserStatus"               ) var UserStatus               : String,
    @SerializedName("PackageName"              ) var PackageName              : String,
    @SerializedName("matchingincome"           ) var matchingincome           : String,
    @SerializedName("sponsormatchingincome"    ) var sponsormatchingincome    : String,
    @SerializedName("singlelineincome"         ) var singlelineincome         : String,
    @SerializedName("cashPointBalance"         ) var cashPointBalance         : String,
    @SerializedName("LeftOrgPoint"             ) var LeftOrgPoint             : String,
    @SerializedName("RightOrgPoint"            ) var RightOrgPoint            : String,
    @SerializedName("LeftOrgUsers"             ) var LeftOrgUsers             : String,
    @SerializedName("rightOrgUsers"            ) var rightOrgUsers            : String,
    @SerializedName("rewardincome"             ) var rewardincome             : String,
    @SerializedName("repurhaseLevelIncome"     ) var repurhaseLevelIncome     : String,
    @SerializedName("AffiliateLevelIncome"     ) var AffiliateLevelIncome     : String,
    @SerializedName("RechargeLevelIncome"      ) var RechargeLevelIncome      : String,
    @SerializedName("repurchaseCaskBackIncome" ) var repurchaseCaskBackIncome : String,
    @SerializedName("RechargeCaskBackIncome"   ) var RechargeCaskBackIncome   : String,
    @SerializedName("AffiliateCaskBackIncome"  ) var AffiliateCaskBackIncome  : String,
    @SerializedName("tds"                      ) var tds                      : String

)