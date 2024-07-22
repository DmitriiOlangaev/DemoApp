package com.demo.demoapp.feature.airTickets.ui.recyclerView

internal sealed interface TownUiState {
    data class Success(val town : String) : TownUiState
    data object Loading : TownUiState
}