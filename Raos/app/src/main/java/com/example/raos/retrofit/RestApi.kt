package com.example.raos.retrofit


import com.example.raos.model.UserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApi {
    @Headers("Content-type:application/json")
    @POST("add")

    fun collectCustomerDetails(@Body userData: UserInfo): Call<UserInfo>
}