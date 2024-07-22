package com.demo.demoapp.feature.airTickets.ui.recyclerView

import com.demo.demoapp.domain.model.Concert

internal sealed interface ConcertUiState {
    data class Success(val concert: Concert) : ConcertUiState
    data object Loading : ConcertUiState
}