package com.fs.compose.login.data.repository

import com.fs.compose.login.data.model.User
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LoginRepository @Inject constructor(private val loginRemoteDataSource: LoginRemoteDataSource) {


    suspend fun getLogin(): User {
        return loginRemoteDataSource.getLogin()
    }

    suspend fun getCreateAccount(): User {
        return loginRemoteDataSource.getCreateAccount()
    }
}