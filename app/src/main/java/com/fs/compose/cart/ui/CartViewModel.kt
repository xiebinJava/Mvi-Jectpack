package com.fs.compose.cart.ui

import com.fs.compose.base.BaseViewModel
import com.fs.compose.base.UiState
import com.fs.compose.cart.data.repository.CartRepository
import com.fs.compose.cart.domain.FormatDataCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


sealed class CartUiState : UiState {
    object Idle : CartUiState()

}

@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepository,private val formatDataCartUseCase: FormatDataCartUseCase)  : BaseViewModel<CartUiState, CartActivity.CartIntent>() {


    override fun createInitialState(): CartUiState {
        return CartUiState.Idle
    }

    override fun handleEvent(event: CartActivity.CartIntent) {
        when (event) {

            else -> {

            }
        }
    }


}