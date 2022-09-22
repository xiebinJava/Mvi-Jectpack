package com.fs.compose.mvi.data.repository

import com.fs.compose.mvi.data.model.Data
import com.fs.compose.mvi.data.model.User
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LoginRepository @Inject constructor(private val loginDataSource: LoginDataSource) {


    suspend fun getLogin(): User {
        return loginDataSource.getLogin()
    }

    suspend fun getCreateAccount(): User {
        return loginDataSource.getCreateAccount()
    }
}