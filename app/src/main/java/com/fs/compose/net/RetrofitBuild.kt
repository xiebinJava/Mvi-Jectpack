package com.fs.compose.net

import com.fs.compose.cart.data.api.CartApiService
import com.fs.compose.login.data.api.LoginApiService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object RetrofitBuild {

    private const val BASE_URL = "https://reqres.in/api/"

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()


    val apiService: LoginApiService = getRetrofit().create(LoginApiService::class.java)
    val cartApiService: CartApiService = getRetrofit().create(CartApiService::class.java)

}