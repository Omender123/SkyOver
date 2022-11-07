package com.sky.skyoverflow.Model.Response

import com.google.gson.annotations.SerializedName

data class CheckLoginResponse(
    @SerializedName("StatusCode") var StatusCode: String,
    @SerializedName("Message") var Message: String,
    @SerializedName("Data") var Data: Data
)

data class Data(

    @SerializedName("UserName") var UserName: String,
    @SerializedName("FirstName") var FirstName: String,
    @SerializedName("LastName") var LastName: String,
    @SerializedName("Mobile") var Mobile: String,
    @SerializedName("Email") var Email: String,
    @SerializedName("profile_pic") var profilePic: String,
    @SerializedName("RegNumber") var RegNumber: String,
    @SerializedName("PanNo") var PanNo: String,
    @SerializedName("AadharNo") var AadharNo: String,
    @SerializedName("Address") var Address: String,
    @SerializedName("BankName") var BankName: String,
    @SerializedName("AccountNo") var AccountNo: String,
    @SerializedName("IfscCode") var IfscCode: String,
    @SerializedName("PayeeName") var PayeeName: String,
    @SerializedName("Package") var Package: String,
    @SerializedName("PackageAmount") var PackageAmount: String,
    @SerializedName("MemberStatus") var MemberStatus: String,
    @SerializedName("JoiningDate") var JoiningDate: String,
    @SerializedName("ActivationDate") var ActivationDate: String,
    @SerializedName("SponserName") var SponserName: String,
    @SerializedName("SponserID") var SponserID: String,
    @SerializedName("active") var active: Int,
    @SerializedName("DOB") var DOB: String,
    @SerializedName("Gender") var Gender: Int,
    @SerializedName("FatherHusbandName") var FatherHusbandName: String,
    @SerializedName("PhoneNumber") var PhoneNumber: String,
    @SerializedName("CountryID") var CountryID: Int,
    @SerializedName("StateID") var StateID: Int,
    @SerializedName("CityID") var CityID: Int,
    @SerializedName("PinCode") var PinCode: Int,
    @SerializedName("WalletAmount") var WalletAmount: String,
    @SerializedName("paytmNo") var paytmNo: String,
    @SerializedName("upino") var upino: String,
    @SerializedName("Branch") var Branch: String,
    @SerializedName("IdProof") var IdProof: String,
    @SerializedName("AddressProof") var AddressProof: String,
    @SerializedName("SignatureProof") var SignatureProof: String,
    @SerializedName("OtpCode") var OtpCode: String

)