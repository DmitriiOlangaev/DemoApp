package com.demo.demoapp.feature.airTickets.ui.recyclerView

import com.demo.demoapp.domain.model.DestinationSuggestion

internal sealed interface ToDestinationUiState {
    data class Success(val destinationSuggestion: DestinationSuggestion) : ToDestinationUiState
    data object Loading : ToDestinationUiState
}