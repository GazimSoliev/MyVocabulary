package com.gazim.myvocabluary.app.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.gazim.myvocabluary.app.extensions.getViewModel
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

abstract class BaseScreen<
    STATE : IState,
    SIDE_EFFECT : ISideEffect,
    ACTION : IAction,
    VIEW_MODEL : BaseViewModel<STATE, SIDE_EFFECT, ACTION>,
    >(kClazz: KClass<VIEW_MODEL>) : AndroidScreen() {

    private val clazz = kClazz.java

    @Transient
    private lateinit var viewModel: VIEW_MODEL

    @Transient
    protected lateinit var navigator: Navigator

    @Transient
    private lateinit var _state: State<STATE>

    protected val state get() = _state.value

    protected abstract suspend fun handleSideEffect(sideEffect: SIDE_EFFECT)

    protected fun sendAction(action: ACTION) = viewModel.handleAction(action)

    @Composable
    override fun Content() {
        viewModel = getViewModel(clazz.kotlin)
        _state = viewModel.container.stateFlow.collectAsState()
        LaunchedEffect(viewModel) {
            viewModel.container.sideEffectFlow.collect { launch { handleSideEffect(it) } }
        }
        navigator = LocalNavigator.currentOrThrow
        LifecycleEffect(
            onStarted = ::onStart,
            onDisposed = ::onDisposed,
        )
        Screen()
    }

    @Composable
    protected abstract fun Screen()

    protected open fun onStart() = Unit

    protected open fun onDisposed() = Unit
}
