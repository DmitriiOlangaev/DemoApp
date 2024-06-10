package com.demo.demoapp.feature.airTickets.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.demoapp.core.repositories.interfaces.ToDestinationSuggestionsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

internal class SearchStateChoosingToFragmentViewModel @AssistedInject constructor(
    @Assisted stateHandle: SavedStateHandle,
    private val toDestinationSuggestionsRepository: ToDestinationSuggestionsRepository
) : ViewModel() {

    internal val toDestinationSuggestionsFlow = toDestinationSuggestionsRepository
        .getToDestinationSuggestions()
        .stateIn(viewModelScope, SharingStarted.Lazily, listOf())

    @AssistedFactory
    internal interface Factory {
        fun create(savedStateHandle: SavedStateHandle): SearchStateChoosingToFragmentViewModel
    }
}