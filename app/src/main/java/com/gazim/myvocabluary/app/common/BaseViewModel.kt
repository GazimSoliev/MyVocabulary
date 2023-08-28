package com.gazim.myvocabluary.app.common

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost

abstract class BaseViewModel<STATE : IState, SIDE_EFFECT : ISideEffect, ACTION : IAction> :
    ContainerHost<STATE, SIDE_EFFECT>, ViewModel() {
    abstract fun handleAction(action: ACTION)
}
