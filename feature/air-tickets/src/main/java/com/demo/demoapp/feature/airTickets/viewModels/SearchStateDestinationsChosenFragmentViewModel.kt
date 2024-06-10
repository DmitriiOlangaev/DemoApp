package com.demo.demoapp.feature.airTickets.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.demoapp.core.repositories.interfaces.TicketsOffersRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

internal class SearchStateDestinationsChosenFragmentViewModel @AssistedInject constructor(
    @Assisted saveStateHandle: SavedStateHandle,
    @Assisted("from") from: String, @Assisted("to") to: String,
    private val ticketsOffersRepository: TicketsOffersRepository
) :
    ViewModel() {
    val ticketsOffersFlow = ticketsOffersRepository.getTicketsOffers(from, to)
        .stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    @AssistedFactory
    interface Factory {
        fun create(
            savedStateHandle: SavedStateHandle,
            @Assisted("from") from: String,
            @Assisted("to") to: String
        ): SearchStateDestinationsChosenFragmentViewModel
    }
}