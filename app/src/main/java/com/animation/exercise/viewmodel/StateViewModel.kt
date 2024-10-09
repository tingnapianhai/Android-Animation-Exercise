package com.animation.exercise.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

abstract class StateViewModel<S> : ViewModel() {
    abstract val stateFlow: MutableStateFlow<S>
    val state: S get() = stateFlow.value

    protected fun updateState(update: S.() -> S) = with(stateFlow) { value = update.invoke(value) }
}
