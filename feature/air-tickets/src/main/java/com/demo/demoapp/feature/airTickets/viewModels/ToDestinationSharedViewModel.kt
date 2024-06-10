package com.demo.demoapp.feature.airTickets.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.channels.Channel

internal class ToDestinationSharedViewModel @AssistedInject constructor(
    @Assisted stateHandle: SavedStateHandle,
    val toDestinationChannel: Channel<String>
) : ViewModel() {

    @AssistedFactory
    internal interface Factory {
        fun create(savedStateHandle: SavedStateHandle): ToDestinationSharedViewModel
    }

    override fun onCleared() {
        toDestinationChannel.close()
        super.onCleared()
    }
}