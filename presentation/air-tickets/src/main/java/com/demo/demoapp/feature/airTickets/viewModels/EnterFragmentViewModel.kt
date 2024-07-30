package com.demo.demoapp.feature.airTickets.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.demoapp.core.common.Result
import com.demo.demoapp.core.common.asResult
import com.demo.demoapp.domain.model.Concert
import com.demo.demoapp.domain.usecases.GetConcertsUseCase
import com.demo.demoapp.domain.usecases.GetPreviouslyEnteredFromUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class EnterFragmentViewModel @AssistedInject constructor(
    @Assisted stateHandle: SavedStateHandle,
    private val getConcertsUseCase: GetConcertsUseCase,
    getPreviouslyEnteredFromUseCase: GetPreviouslyEnteredFromUseCase
) : ViewModel() {
    val fromStateFlow: StateFlow<Result<String>> =
        getPreviouslyEnteredFromUseCase().map { it.lastOrNull() ?: "" }.asResult()
            .stateIn(viewModelScope, SharingStarted.Lazily, Result.Loading)
    var concertsStateFlow: StateFlow<Result<List<Concert>>> = concertsStateFlow()
        private set

    fun refetchConcerts() {
        concertsStateFlow = concertsStateFlow()
    }


    private fun concertsStateFlow() = getConcertsUseCase().asResult().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        Result.Loading
    )


    @AssistedFactory
    internal interface Factory {
        fun create(stateHandle: SavedStateHandle): EnterFragmentViewModel
    }
}
