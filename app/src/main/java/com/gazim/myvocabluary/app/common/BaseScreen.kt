package com.gazim.myvocabluary.app.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import com.gazim.myvocabluary.app.extensions.koinViewModel
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

abstract class BaseScreen<
        STATE : IState,
        SIDE_EFFECT : ISideEffect,
        ACTION : IAction,
        VIEW_MODEL : BaseViewModel<STATE, SIDE_EFFECT, ACTION>,
        >(private val clazz: KClass<VIEW_MODEL>) {

    private lateinit var viewModel: VIEW_MODEL
    private lateinit var _state: State<STATE>
    protected val state get() = _state.value

    protected abstract suspend fun handleSideEffect(sideEffect: SIDE_EFFECT)

    protected fun sendAction(action: ACTION) = viewModel.handleAction(action)

    @Composable
    fun Content() {
        viewModel = koinViewModel(clazz)
        _state = viewModel.container.stateFlow.collectAsState()
        LaunchedEffect(viewModel) {
            viewModel.container.sideEffectFlow.collect { launch { handleSideEffect(it) } }
        }
        Screen()
    }

    @Composable
    abstract fun Screen()
}
