package com.fs.compose.net

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object HttpRequest {

    private const val BASE_URL = "https://reqres.in/api/"


    fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()


    inline fun <reified ApiService> getService(): ApiService {
        return getRetrofit().create(ApiService::class.java)
    }

}