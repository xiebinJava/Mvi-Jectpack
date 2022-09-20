package com.fs.compose.mvi.ui

import androidx.lifecycle.viewModelScope
import com.fs.compose.base.BaseViewModel
import com.fs.compose.mvi.data.repository.LoginRepository
import kotlinx.coroutines.launch


/**
 *  业务逻辑决定着如何处理状态变化。如前面所述，一个例子是在案例研究应用中为报道添加书签。业务逻辑通常位于网域层或数据层中，但绝不能位于界面层中。

    界面行为逻辑或界面逻辑决定着如何在屏幕上显示状态变化。
    示例包括：使用 Android Resources 获取要在屏幕上显示的正确文本、在用户点击某个按钮时转到特定屏幕，或使用消息框或信息提示控件在屏幕上向用户显示消息。
    界面逻辑（尤其是在涉及 Context 等界面类型时）应位于界面中，而非 ViewModel 中。如果界面变得越来越复杂，并且您希望将界面逻辑委托给另一个类，以便有利于进行测试和关注点分离，
    您可以创建一个简单的类作为状态容器。在界面中创建的简单类可以采用 Android SDK 依赖项，因为它们遵循界面的生命周期；ViewModel 对象具有更长的生命周期。

 */

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