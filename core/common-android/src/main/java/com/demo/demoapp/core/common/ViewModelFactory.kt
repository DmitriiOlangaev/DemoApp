package com.demo.demoapp.core.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

class ViewModelFactory<T : ViewModel>(
    savedStateRegistryOwner: SavedStateRegistryOwner,
    private val create: (stateHandle: SavedStateHandle) -> T
) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return create.invoke(handle) as T
    }
}

inline fun <reified T : ViewModel> Fragment.lazyViewModel(
    noinline create: (stateHandle: SavedStateHandle) -> T
) = viewModels<T> {
    ViewModelFactory(this, create)
}