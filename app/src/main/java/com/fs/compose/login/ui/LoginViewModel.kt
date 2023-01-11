package com.fs.compose.login.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.fs.compose.base.BaseViewModel
import com.fs.compose.base.UiState
import com.fs.compose.login.data.model.User
import com.fs.compose.login.data.repository.LoginRepository
import com.fs.compose.login.domain.FormatDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject


/**
 *  业务逻辑决定着如何处理状态变化。如前面所述，一个例子是在案例研究应用中为报道添加书签。业务逻辑通常位于网域层或数据层中，但绝不能位于界面层中。

界面行为逻辑或界面逻辑决定着如何在屏幕上显示状态变化。
示例包括：使用 Android Resources 获取要在屏幕上显示的正确文本、在用户点击某个按钮时转到特定屏幕，或使用消息框或信息提示控件在屏幕上向用户显示消息。
界面逻辑（尤其是在涉及 Context 等界面类型时）应位于界面中，而非 ViewModel 中。如果界面变得越来越复杂，并且您希望将界面逻辑委托给另一个类，以便有利于进行测试和关注点分离，
您可以创建一个简单的类作为状态容器。在界面中创建的简单类可以采用 Android SDK 依赖项，因为它们遵循界面的生命周期；ViewModel 对象具有更长的生命周期。

 */


 data class LoginState(
    val loding: Boolean = false,
    val user: User? = null,
    val error: String? = ""
) : UiState


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val formatDataUseCase: FormatDataUseCase
) :
    BaseViewModel<LoginState, LoginActivity.UserEvent>() {



    /**
     * 处理数据
     */
    init {
        viewModelScope.launch {
            val today = Calendar.getInstance()
            val formatDataUseCase1 = formatDataUseCase(today)
            Log.e("xiebin", formatDataUseCase1.toString())
        }

    }


    /**
     * Android架构指南中没有写这个
     * 目的：进一步解耦，方便viewModel对事件的统一处理
     * 简单的页面可以不使用此方式进行分发直接用viewmodel调用方法
     * 复杂的页面，需要对事件进行分发，方便后期bug定位
     */
    override fun handleEvent(event: LoginActivity.UserEvent) {
        when (event) {
            is LoginActivity.UserEvent.UserLogin -> loginAction()

            is LoginActivity.UserEvent.CreateAccount -> createAccountAction()
        }

    }

    private fun createAccountAction() {
        viewModelScope.launch {

            setState { copy(loding = true) }
            try {
                val createAccount = loginRepository.getCreateAccount()
                setState { copy(user = createAccount) }
            } catch (e: Exception) {
                setState { copy(error = "错误") }
            }

        }


    }

    private fun loginAction() {
        viewModelScope.launch {
            setState { copy(loding = true) }
            try {
                val login = loginRepository.getLogin()
                setState { copy(user = login) }
            } catch (e: Exception) {
                setState { copy(error = e.message.toString()) }
                setState { copy(loding = false) }
            }
            setState { copy(loding = false) }
        }

    }

    override fun createInitialState(): LoginState {
        return LoginState()
    }
}