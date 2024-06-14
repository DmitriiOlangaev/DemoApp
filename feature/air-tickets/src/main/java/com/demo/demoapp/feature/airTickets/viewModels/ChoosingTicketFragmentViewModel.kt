package com.demo.demoapp.feature.airTickets.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.demoapp.core.repositories.interfaces.TicketsRepository
import com.demo.demoapp.feature.airTickets.ui.fragments.TicketData
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

internal class ChoosingTicketFragmentViewModel @AssistedInject constructor(@Assisted stateHandle: SavedStateHandle, @Assisted ticketData: TicketData, private val ticketsRepository: TicketsRepository) : ViewModel(){
    internal val ticketsFlow = ticketsRepository.getTickets(ticketData.from, ticketData.to).stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    @AssistedFactory
    internal interface Factory {
        fun create(stateHandle: SavedStateHandle, ticketData: TicketData) : ChoosingTicketFragmentViewModel
    }
}