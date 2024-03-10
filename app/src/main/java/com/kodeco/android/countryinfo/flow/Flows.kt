package com.kodeco.android.countryinfo.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object Flows {
    init {
        GlobalScope.launch {
            repeat(Int.MAX_VALUE) {
                delay(1000)
                _counterFlow.value += 1
            }
        }
    }
    private var _tapFlow = MutableStateFlow(0)
    val tapFlow :StateFlow<Int> get() = _tapFlow
    private var _backFlow = MutableStateFlow(0)
    val backFlow: StateFlow<Int> get() = _backFlow
    private val _counterFlow = MutableStateFlow(0)
    val counterFlow: StateFlow<Int> get() = _counterFlow

    fun tap() {
        _tapFlow.value += 1
    }

    fun tapBack() {
        _backFlow.value += 1
    }

    fun resetCounterFlow() {
        _counterFlow.value = 0
    }
}
