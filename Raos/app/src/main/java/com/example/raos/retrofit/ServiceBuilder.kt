package com.example.raos.retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

//    private var okHttpClient: Any
    private const val URL ="http://192.168.100.156:0891/api/v1/kycdata/"

    private val logger = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY
    )

    private val client = OkHttpClient.Builder().addInterceptor(logger)

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client.build())
        .build()

    fun<T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }

}