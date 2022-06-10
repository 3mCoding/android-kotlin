package com.mirim.a3mcoding.network

import com.mirim.a3mcoding.server.NullOnEmptyConverter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        val BASE_URL = "http://54.225.221.89:3000/"
        var serviceAPI: ServiceAPI

        val retrofit : Retrofit
            get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(NullOnEmptyConverter())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        init {
            serviceAPI = retrofit.create(ServiceAPI::class.java)
        }
    }
}