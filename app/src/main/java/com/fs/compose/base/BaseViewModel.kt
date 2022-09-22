package com.fs.compose.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

interface UiState

interface UiEvent

/**
 * 状态向下流动、事件向上流动的这种模式称为单向数据流 (UDF)。这种模式对应用架构的影响如下：

    ViewModel 会存储并公开界面要使用的状态。界面状态是经过 ViewModel 转换的应用数据。
    界面会向 ViewModel 发送用户事件通知。
    ViewModel 会处理用户操作并更新状态。
    更新后的状态将反馈给界面以进行呈现。
    系统会对导致状态更改的所有事件重复上述操作。
 */

abstract class BaseViewModel<State: UiState, Event: UiEvent> : ViewModel() {

    // 初始状态
    private val initialState: State by lazy { createInitialState() }



    // 页面需要的状态，对应于 MVI 模式的 ViewState
    private val _uiState = MutableStateFlow<State>(initialState)

    // 对外接口使用不可变版本
    val uiState = _uiState.asStateFlow()



    private val userIntent = Channel<Event>(Channel.UNLIMITED)
    // 页面的事件操作，对应于 MVI 模式的 Intent
//    private val _event = MutableSharedFlow<Event>()


    init {
        viewModelScope.launch {
//            _event.collect {
//                handleEvent(it)
//            }

            userIntent.consumeAsFlow().collect {
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
//            _event.emit(event)
            userIntent.send(event)
        }
    }

    /**
     * 状态变更
     */
    protected fun setState(newState: State) {
        _uiState.value = newState
    }




}