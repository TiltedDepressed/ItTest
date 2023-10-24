package com.example.ittestapplication.datasource

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        // http://188.234.244.32/apidocs/
        // http://188.234.244.32:8090/api/  http://172.30.44.151:8090/api/
        .baseUrl("http://172.30.44.151:8090/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }

}