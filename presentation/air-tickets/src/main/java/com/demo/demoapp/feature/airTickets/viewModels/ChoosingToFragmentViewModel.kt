package com.demo.demoapp.feature.airTickets.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.demoapp.core.common.Result
import com.demo.demoapp.core.common.asResult
import com.demo.demoapp.domain.usecases.GetToDestinationsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

internal class ChoosingToFragmentViewModel @AssistedInject constructor(@Assisted stateHandle: SavedStateHandle,private val getToDestinationsUseCase : GetToDestinationsUseCase) : ViewModel(){
    val toDestinationsStateFlow = getToDestinationsUseCase().asResult().stateIn(viewModelScope, SharingStarted.Lazily, Result.Loading)
    @AssistedFactory
    interface Factory {
        fun create(stateHandle: SavedStateHandle): ChoosingToFragmentViewModel
    }
}