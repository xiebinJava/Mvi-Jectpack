package com.fs.compose.mvi.data.repository

import com.fs.compose.mvi.data.api.LoginApiService
import com.fs.compose.net.HttpRequest

object LoginRepository {

    private val loginApiService: LoginApiService by lazy {
        HttpRequest.getService()
    }

    suspend fun getLogin() = loginApiService.getUsers()
    suspend fun getCreateAccount() = loginApiService.getCreateAccount()


}