package com.sky.skyoverflow.Model.Response

import com.google.gson.annotations.SerializedName

data class ProfileDetailsResponse (
    @SerializedName("StatusCode" ) var StatusCode : String,
    @SerializedName("Message"    ) var Message    : String,
    @SerializedName("Data"       ) var profileDetails: ProfileDetails)

data class ProfileDetails (

    @SerializedName("name"           ) var name           : String,
    @SerializedName("lName"          ) var lName          : String,
    @SerializedName("title"          ) var title          : String,
    @SerializedName("memMobile"      ) var memMobile      : String,
    @SerializedName("loginid"        ) var loginid        : String,
    @SerializedName("sponserid"      ) var sponserid      : String,
    @SerializedName("sponsername"    ) var sponsername    : String,
    @SerializedName("sponserMobile"  ) var sponserMobile  : String,
    @SerializedName("gender"         ) var gender         : String,
    @SerializedName("fat_husName"    ) var fatHusName     : String,
    @SerializedName("display_pic"    ) var displayPic     : String,
    @SerializedName("cid"            ) var cid            : String,
    @SerializedName("sid"            ) var sid            : String,
    @SerializedName("ctid"           ) var ctid           : String,
    @SerializedName("pinCode"        ) var pinCode        : String,
    @SerializedName("pannumber"      ) var pannumber      : String,
    @SerializedName("Aadhar"         ) var Aadhar         : String,
    @SerializedName("address"        ) var address        : String,
    @SerializedName("bankname"       ) var bankname       : String,
    @SerializedName("accountNo"      ) var accountNo      : String,
    @SerializedName("payeename"      ) var payeename      : String,
    @SerializedName("IFSCCode"       ) var IFSCCode       : String,
    @SerializedName("Branch"         ) var Branch         : String,
    @SerializedName("NameOnAccount"  ) var NameOnAccount  : String,
    @SerializedName("cityName"       ) var cityName       : String,
    @SerializedName("stateName"      ) var stateName      : String,
    @SerializedName("emailID"        ) var emailID        : String,
    @SerializedName("IdProof"        ) var IdProof        : String,
    @SerializedName("AddressProof"   ) var AddressProof   : String,
    @SerializedName("SignatureProof" ) var SignatureProof : String,
    @SerializedName("branchCode"     ) var branchCode     : String,
    @SerializedName("dob"            ) var dob            : String,
    @SerializedName("joiningdate"    ) var joiningdate    : String,
    @SerializedName("activationdate" ) var activationdate : String,
    @SerializedName("Renewaldate"    ) var Renewaldate    : String,
    @SerializedName("active"         ) var active         : String,
    @SerializedName("memberStatus"   ) var memberStatus   : String,
    @SerializedName("kitprice"       ) var kitprice       : String,
    @SerializedName("kitcode"        ) var kitcode        : String,
    @SerializedName("paytmno"        ) var paytmno        : String,
    @SerializedName("upino"          ) var upino          : String

) : java.io.Serializable
