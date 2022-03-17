package com.example.raos.ViewModel

import androidx.lifecycle.ViewModel
import java.util.*

class KycViewModel:ViewModel() {

    var doB = ""
    var fName = ""
    var mName = ""
    var lName = ""
    var languagee = ""
    var genderr = ""
    var nIdentity = ""
    var eMail = ""
    var homeEmaill = ""
    var phoneNoo = ""
    var pCell = ""
    var pHome1 = ""
    var pHome2 = ""
    var preferredPhone = ""
    var occupations = ""
    var hEducation = ""
    var placeOfBirth = ""
    var pAddress = ""
    var state = ""
    var zip = ""


    fun holdCollectedData1(dob:String,firstname:String,middleName:String,lastName:String,
    language:String,gender:String, nationalIdd:String){

        doB =dob
        fName = firstname
        mName = middleName
        lName = lastName
        languagee = language
        genderr = gender
        nIdentity = nationalIdd


    }

    fun holdCollectedData2(email:String, homeEmail:String,phoneNo:String,
    phoneCell:String,phoneHome1:String, phoneHome2:String, preferendPhone:String){
        eMail = email
        homeEmaill =homeEmail
        phoneNoo = phoneNo
        pCell = phoneCell
        pHome1 =phoneHome1
        pHome2 = phoneHome2
        preferredPhone = preferendPhone


    }

    fun holdCOllectedData3(occupation:String,education:String,poB:String,
    physicalAddress:String,statee:String,ziip:String){
        occupations = occupation
        hEducation = education
        placeOfBirth = poB
        pAddress = physicalAddress
        state = statee
        zip = ziip

    }



}