package com.mirim.a3mcoding.network

import com.mirim.a3mcoding.server.NullOnEmptyConverter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        val BASE_URL = "https://tmc.emirim.kr/"
        var serviceAPI: ServiceAPI

        val retrofit : Retrofit
            get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        init {
            serviceAPI = retrofit.create(ServiceAPI::class.java)
        }
    }
}