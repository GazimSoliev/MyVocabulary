package com.gazim.myvocabluary.app.extensions

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import org.koin.androidx.compose.defaultExtras
import org.koin.androidx.viewmodel.resolveViewModel
import org.koin.compose.LocalKoinScope
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.Scope
import kotlin.reflect.KClass

@Composable
fun <T : ViewModel> getViewModel(
    clazz: KClass<T>,
    qualifier: Qualifier? = null,
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    },
    key: String? = null,
    extras: CreationExtras = defaultExtras(viewModelStoreOwner),
    scope: Scope = LocalKoinScope.current,
    parameters: ParametersDefinition? = null,
): T {
    return koinViewModel(clazz, qualifier, viewModelStoreOwner, key, extras, scope, parameters)
}

@OptIn(KoinInternalApi::class)
@Composable
fun <T : ViewModel> koinViewModel(
    clazz: KClass<T>,
    qualifier: Qualifier? = null,
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    },
    key: String? = null,
    extras: CreationExtras = defaultExtras(viewModelStoreOwner),
    scope: Scope = LocalKoinScope.current,
    parameters: ParametersDefinition? = null,
): T = resolveViewModel(
    clazz, viewModelStoreOwner.viewModelStore, key, extras, qualifier, scope, parameters
)