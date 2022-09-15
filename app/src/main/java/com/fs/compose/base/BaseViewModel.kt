package com.fs.compose.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface UiState

interface UiEvent

interface UiEffect

abstract class BaseViewModel<State: UiState, Event: UiEvent> : ViewModel() {

    // 初始状态
    private val initialState: State by lazy { createInitialState() }

    // 页面需要的状态，对应于 MVI 模式的 ViewState
    private val _uiState = MutableStateFlow<State>(initialState)

    // 对外接口使用不可变版本
    val uiState = _uiState.asStateFlow()

    // 页面状态变更的 “副作用”，类似一次性事件，不需要重放的状态变更（例如 Toast）
//    private val _effect = MutableSharedFlow<Effect>()

    // 对外接口使用不可变版本
//    val effect = _effect.asSharedFlow()

    // 页面的事件操作，对应于 MVI 模式的 Intent
    private val _event = MutableSharedFlow<Event>()

    init {
        viewModelScope.launch {
            _event.collect {
                handleEvent(it)
            }
        }
    }

    // 初始状态
    protected abstract fun createInitialState(): State

    // 事件处理
    protected abstract fun handleEvent(event: Event)

    /**
     * 事件入口
     */
    fun sendEvent(event: Event) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }

    /**
     * 状态变更
     */
    protected fun setState(newState: State) {
        _uiState.value = newState
    }
//
//    /**
//     * 副作用
//     */
//    protected fun setEffect(effect: Effect) {
//        viewModelScope.launch {
//            _effect.emit(effect)
//        }
//    }

}