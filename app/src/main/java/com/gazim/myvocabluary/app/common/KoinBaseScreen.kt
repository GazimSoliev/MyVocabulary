package com.gazim.myvocabluary.app.common

import androidx.compose.runtime.Composable
import com.gazim.myvocabluary.app.extensions.koinViewModel
import kotlin.reflect.KClass

abstract class KoinBaseScreen<
    STATE : IState,
    SIDE_EFFECT : ISideEffect,
    ACTION : IAction,
    VIEW_MODEL : BaseViewModel<STATE, SIDE_EFFECT, ACTION>,
    >(
    private val clazz: KClass<VIEW_MODEL>,
) : BaseScreen<STATE, SIDE_EFFECT, ACTION, VIEW_MODEL>() {
    @Composable
    override fun createViewModel(): VIEW_MODEL = koinViewModel(clazz)
}
