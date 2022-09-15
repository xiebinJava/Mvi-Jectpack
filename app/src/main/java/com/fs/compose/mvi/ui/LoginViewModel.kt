package com.fs.compose.mvi.ui

import androidx.lifecycle.viewModelScope
import com.fs.compose.base.BaseViewModel
import com.fs.compose.mvi.data.repository.LoginRepository
import kotlinx.coroutines.launch


class LoginViewModel : BaseViewModel<LoginActivity.LoginUiState, LoginActivity.UserIntent>() {

    override fun createInitialState(): LoginActivity.LoginUiState {
        return LoginActivity.LoginUiState.Idle
    }

    override fun handleEvent(event: LoginActivity.UserIntent) {
        when (event) {
            is LoginActivity.UserIntent.UserLogin -> loginAction()

            is LoginActivity.UserIntent.CreateAccount -> createAccountAction()
        }

    }

    private fun createAccountAction() {
        viewModelScope.launch {
            setState(LoginActivity.LoginUiState.Loading)
            try {
                setState(LoginActivity.LoginUiState.CreateAccount(LoginRepository.getCreateAccount()))
            } catch (e: Exception) {
                setState(LoginActivity.LoginUiState.Error("错误"))
            }
        }


    }

    private fun loginAction() {
        viewModelScope.launch {

            setState(LoginActivity.LoginUiState.Loading)
            try {
                setState(LoginActivity.LoginUiState.Users(LoginRepository.getLogin()))
            } catch (e: Exception) {
                setState(LoginActivity.LoginUiState.Error("错误"))
            }

        }
    }

}