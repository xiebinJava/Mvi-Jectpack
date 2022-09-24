package com.fs.compose.cart.data.api
import com.fs.compose.login.data.model.User
import retrofit2.http.GET



interface CartApiService  {

    @GET("users")
    suspend fun getUsers(): User

    @GET("users")
    suspend fun getCreateAccount(): User

}