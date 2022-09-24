package com.fs.compose.login.data.api
import com.fs.compose.login.data.model.User
import retrofit2.http.GET



interface LoginApiService  {

    @GET("users")
    suspend fun getUsers(): User

    @GET("users")
    suspend fun getCreateAccount(): User

}