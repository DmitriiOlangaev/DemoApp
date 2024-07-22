package com.demo.demoapp.feature.airTickets.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.demoapp.core.common.Result
import com.demo.demoapp.core.common.asResult
import com.demo.demoapp.domain.usecases.EnterFromUseCase
import com.demo.demoapp.domain.usecases.EnterToUseCase
import com.demo.demoapp.domain.usecases.GetPreviouslyEnteredFromUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class ChoosingDestsFragmentViewModel @AssistedInject constructor(
    @Assisted stateHandle: SavedStateHandle,
    private val enterFromUseCase: EnterFromUseCase,
    private val enterToUseCase : EnterToUseCase,
    previouslyEnteredFromUseCase: GetPreviouslyEnteredFromUseCase
) : ViewModel() {
    val fromStateFlow = previouslyEnteredFromUseCase().map{it.last()}.asResult().stateIn(viewModelScope, SharingStarted.Lazily, Result.Loading)

    fun enterFrom(from: String) {
        viewModelScope.launch {
            enterFromUseCase(from)
        }
    }

    fun enterTo(to : String) {
        viewModelScope.launch {
            enterToUseCase(to)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(stateHandle: SavedStateHandle): ChoosingDestsFragmentViewModel
    }
}