package com.example.raos.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserInfo(
    @SerializedName("id")
    var id:Int?=0,
    @SerializedName("cust_dob")
    var dob: String?,
    @SerializedName("cust_first_name")
    var firstName: String?,
    @SerializedName("cust_language")
    var language: String?,
    @SerializedName("cust_last_name")
    var lastName:String?,
    @SerializedName("cust_middle_name")
    var middleName:String?,
    @SerializedName("email")
    var email:String?,
    @SerializedName("education")
    var education:String?,
    @SerializedName("email_home")
    var emailHome:String?,
    @SerializedName("gender")
    var gender:String?,
    @SerializedName("nat_id_card_num")
    var nIdentity:String?,
    @SerializedName("occupation")
    var occupation:String?,
    @SerializedName("phone")
    var phoneNo:String?,
    @SerializedName("phone_cell")
    var phoneCell:String?,
    @SerializedName("phone_home")
    var phoneHome:String?,
    @SerializedName("phone_home2")
    var phoneHome2:String?,
    @SerializedName("physical_state")
    var physicalState:String?,
    @SerializedName("preferredphone")
    var preferredPhone:String?,
    @SerializedName("state")
    var state:String?,
    @SerializedName("zip")
    var zip:String?
)
