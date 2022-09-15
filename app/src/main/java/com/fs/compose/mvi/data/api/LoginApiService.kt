package com.fs.compose.mvi.data.api

import com.fs.compose.mvi.data.model.User
import retrofit2.http.GET

interface LoginApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users")
    suspend fun getCreateAccount(): User

}