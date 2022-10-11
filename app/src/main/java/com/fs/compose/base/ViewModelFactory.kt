package com.fs.compose.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fs.compose.login.data.repository.LoginRemoteDataSource
import com.fs.compose.login.data.repository.LoginRepository
import com.fs.compose.login.domain.FormatDataUseCase
import com.fs.compose.login.ui.LoginViewModel

class ViewModelFactory(private val loginRepository: LoginRepository,private val formatDataUseCase: FormatDataUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(loginRepository,formatDataUseCase) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}