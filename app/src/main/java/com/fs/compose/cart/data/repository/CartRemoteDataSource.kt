package com.fs.compose.cart.data.repository

import com.fs.compose.cart.data.api.CartApiService
import com.fs.compose.login.data.api.LoginApiService
import com.fs.compose.login.data.model.User
import com.fs.compose.net.RetrofitBuild
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CartRemoteDataSource @Inject constructor(){

    private val cartApi: CartApiService by lazy {
        RetrofitBuild.cartApiService
    }
    private val ioDispatcher: CoroutineDispatcher by lazy {
        Dispatchers.IO
    }

    suspend fun getCart(): User = withContext(ioDispatcher) {
        cartApi.getUsers()
    }


}