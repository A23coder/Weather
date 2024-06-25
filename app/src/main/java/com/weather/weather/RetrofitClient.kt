package com.weather.weather

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val BASE_URL = "https://api.weatherapi.com/v1/"
    val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
object ApiClient{
    val apiService:ApiService by lazy {
        RetrofitClient.retrofit.create(ApiService::class.java)
    }
}