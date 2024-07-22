package com.demo.demoapp.feature.airTickets.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel

internal class TownClickedSharedViewModel : ViewModel() {
    val channel : Channel<String> = Channel(capacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
}