package com.example.raos.Respository

import com.example.raos.retrofit.RestApi
import com.example.raos.retrofit.ServiceBuilder
import com.example.raos.model.UserInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class ApiManager {
    fun collectCustomerDetails(userData: UserInfo, onResult:(UserInfo?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.collectCustomerDetails(userData).enqueue(
            object : Callback<UserInfo> {
                 override fun onFailure(call: Call<UserInfo>, t: Throwable){
                    onResult(null)
                }
                 override fun onResponse(call: Call<UserInfo>, response:Response<UserInfo>){
                    val collectedCustomerDetails = response.body()
                    onResult(collectedCustomerDetails)
                }

            }
        )
    }
}

