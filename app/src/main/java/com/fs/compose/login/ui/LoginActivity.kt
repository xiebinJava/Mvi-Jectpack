package com.fs.compose.login.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.fs.compose.base.UiEvent
import com.fs.compose.base.ViewModelFactory
import com.fs.compose.cart.data.repository.CartRemoteDataSource
import com.fs.compose.cart.data.repository.CartRepository
import com.fs.compose.cart.domain.FormatDataCartUseCase
import com.fs.compose.login.data.repository.LoginRemoteDataSource
import com.fs.compose.login.data.repository.LoginRepository
import com.fs.compose.login.domain.FormatDataUseCase
import com.fs.compose.ui.theme.ComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {


    /**
     * 用了Hilt依赖注入框架
     */
    private val loginViewModel:LoginViewModel by viewModels()

    /**
     * 没用Hilt依赖注入框架
     */
//    private val loginViewModel: LoginViewModel by lazy {
//        ViewModelProviders.of(
//            this, ViewModelFactory(
//                LoginRepository(LoginRemoteDataSource()), FormatDataUseCase(
//                    LoginRepository(LoginRemoteDataSource()),
//                    CartRepository(CartRemoteDataSource()),
//                    FormatDataCartUseCase(CartRepository(CartRemoteDataSource()))
//                )
//            )
//        )[LoginViewModel::class.java]
//    }


    //定义用户intent
    //Kotlin中的密封类的出现，在于它定义了一种受限的类继承结构，可以保证我们写出更安全的代码。
    sealed class UserIntent : UiEvent {
        object UserLogin : UserIntent()
        object CreateAccount : UserIntent()


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {

                Column {
                    loginButton()

                }

            }

        }

        observeViewModel()

    }

    @Composable
    private fun loginButton() {

        Button(onClick = { doLogin() }) {
            Text(text = "请求网络")
        }
    }

    private fun doLogin() {

        lifecycleScope.launch {
            loginViewModel.sendEvent(UserIntent.UserLogin)
        }
    }

    private fun doCreateAccount() {
        lifecycleScope.launch {

            loginViewModel.sendEvent(UserIntent.CreateAccount)
        }
    }


    private fun observeViewModel() {

        lifecycleScope.launch {
            loginViewModel.uiState.collect {
                when (it) {
                    is LoginUiState.Idle -> {

                    }
                    is LoginUiState.Loading -> {
                        Log.e("xiebin", "loding")
                    }

                    is LoginUiState.UsersInfo -> {
                        Log.e("xiebin", it.user?.data!![0].first_name)
                    }
                    is LoginUiState.Error -> {
                        Log.e("xiebin", it.error.toString())
                    }
                    else -> {}
                }

            }
        }
    }

}