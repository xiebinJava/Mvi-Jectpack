package com.fs.compose.cart.data.repository

import com.fs.compose.login.data.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor(private val cartRemoteDataSource: CartRemoteDataSource){


    suspend fun getCart(): User {
        return cartRemoteDataSource.getCart()
    }

}