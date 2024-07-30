package com.demo.demoapp.presentation.air.tickets.compose.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.demoapp.core.common.Result
import com.demo.demoapp.core.common.asResult
import com.demo.demoapp.domain.usecases.EnterFromUseCase
import com.demo.demoapp.domain.usecases.EnterToUseCase
import com.demo.demoapp.domain.usecases.GetPreviouslyEnteredFromUseCase
import com.demo.demoapp.domain.usecases.GetToDestinationsUseCase
import com.demo.demoapp.domain.usecases.GetTownsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ChoosingDestsScreenViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val enterFromUseCase: EnterFromUseCase,
    private val enterToUseCase: EnterToUseCase,
    getPreviouslyEnteredFromUseCase: GetPreviouslyEnteredFromUseCase,
    getToDestinationsUseCase: GetToDestinationsUseCase,
    getTownsUseCase: GetTownsUseCase
) : ViewModel() {
    val fromStateFlow = getPreviouslyEnteredFromUseCase().map { it.last() }.asResult()
        .stateIn(viewModelScope, SharingStarted.Lazily, Result.Loading)
    val prevEnteredFromStateFlow: StateFlow<Result<List<String>>> =
        getPreviouslyEnteredFromUseCase().asResult()
            .stateIn(viewModelScope, SharingStarted.Lazily, Result.Loading)
    val toDestinationsStateFlow = getToDestinationsUseCase().asResult()
        .stateIn(viewModelScope, SharingStarted.Lazily, Result.Loading)
    val townsStateFlow: StateFlow<Result<List<String>>> =
        getTownsUseCase().asResult().stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            Result.Loading
        )

    fun enterFrom(from: String) {
        viewModelScope.launch {
            enterFromUseCase(from)
        }
    }

    fun enterTo(to: String) {
        viewModelScope.launch {
            enterToUseCase(to)
        }
    }
}