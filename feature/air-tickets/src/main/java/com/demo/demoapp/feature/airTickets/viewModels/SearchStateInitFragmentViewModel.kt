package com.demo.demoapp.feature.airTickets.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.demoapp.core.model.Concert
import com.demo.demoapp.core.repositories.interfaces.ConcertsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

internal class SearchStateInitFragmentViewModel @AssistedInject constructor(
    @Assisted stateHandle: SavedStateHandle,
    private val concertsRepository: ConcertsRepository
) : ViewModel() {
    val concertsFlow: StateFlow<List<Concert>> =
        concertsRepository.getConcerts().stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    @AssistedFactory
    internal interface Factory {
        fun create(savedStateHandle: SavedStateHandle): SearchStateInitFragmentViewModel
    }

}