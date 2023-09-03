package com.gazim.myvocabluary.app.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import com.bumble.appyx.navigation.lifecycle.DefaultPlatformLifecycleObserver
import com.bumble.appyx.navigation.modality.BuildContext
import com.bumble.appyx.navigation.node.Node
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import kotlin.reflect.KClass

abstract class BaseScreen<
        STATE : IState,
        SIDE_EFFECT : ISideEffect,
        ACTION : IAction,
        VIEW_MODEL : BaseViewModel<STATE, SIDE_EFFECT, ACTION>
        >(
    buildContext: BuildContext, clazz: KClass<VIEW_MODEL>
) : Node(buildContext = buildContext) {

    init {
        @Suppress("LeakingThis")
        lifecycle.addObserver(observer = object : DefaultPlatformLifecycleObserver {
            override fun onCreate() = this@BaseScreen.onCreate()
            override fun onPause() = this@BaseScreen.onPause()
            override fun onResume() = this@BaseScreen.onResume()
            override fun onStart() = this@BaseScreen.onStart()
            override fun onStop() = this@BaseScreen.onStop()
            override fun onDestroy() {
                this@BaseScreen.onDestroy()
                viewModel.viewModelScope.cancel()
            }
        })
    }

    private val viewModel: VIEW_MODEL by inject(clazz = clazz.java)
    private lateinit var _state: State<STATE>
    protected val state get() = _state.value

    protected abstract suspend fun handleSideEffect(sideEffect: SIDE_EFFECT)

    protected fun sendAction(action: ACTION) = viewModel.handleAction(action)

    @Composable
    override fun View(modifier: Modifier) {
        _state = viewModel.container.stateFlow.collectAsState()
        LaunchedEffect(viewModel) {
            viewModel.container.sideEffectFlow.collect { launch { handleSideEffect(it) } }
        }
        Screen()
    }

    @Composable
    abstract fun Screen()

    open fun onCreate() = Unit
    open fun onStart() = Unit
    open fun onResume() = Unit
    open fun onPause() = Unit
    open fun onStop() = Unit
    open fun onDestroy() = Unit
}
