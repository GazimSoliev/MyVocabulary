package com.gazim.myvocabluary.app.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.launch

abstract class BaseScreen<
    STATE : IState,
    SIDE_EFFECT : ISideEffect,
    ACTION : IAction,
    VIEW_MODEL : BaseViewModel<STATE, SIDE_EFFECT, ACTION>,
    > {

    private lateinit var viewModel: VIEW_MODEL
    private lateinit var _state: State<STATE>
    protected val state get() = _state.value

    protected abstract suspend fun handleSideEffect(sideEffect: SIDE_EFFECT)

    @Composable
    protected abstract fun createViewModel(): VIEW_MODEL

    protected fun sendAction(action: ACTION) = viewModel.handleAction(action)

    @Composable
    fun Content() {
        viewModel = createViewModel()
        _state = viewModel.container.stateFlow.collectAsState()
        LaunchedEffect(viewModel) {
            viewModel.container.sideEffectFlow.collect { launch { handleSideEffect(it) } }
        }
        Screen()
    }

    @Composable
    abstract fun Screen()
}
