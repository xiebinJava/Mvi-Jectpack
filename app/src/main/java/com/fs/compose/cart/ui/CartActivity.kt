package com.fs.compose.cart.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.fs.compose.base.UiEvent
import com.fs.compose.login.ui.LoginActivity
import dagger.hilt.android.AndroidEntryPoint





@AndroidEntryPoint
class CartActivity : ComponentActivity() {

    sealed class CartIntent : UiEvent{
        object UserLogin : CartIntent()

    }

    private val cartViewModel:CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


}