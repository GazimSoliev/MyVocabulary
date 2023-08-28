package com.gazim.myvocabluary.app.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.SettingsBuilder
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax

abstract class BaseViewModel<STATE : IState, SIDE_EFFECT : ISideEffect, ACTION : IAction> :
    ContainerHost<STATE, SIDE_EFFECT>, ViewModel() {
    abstract fun handleAction(action: ACTION)
}
