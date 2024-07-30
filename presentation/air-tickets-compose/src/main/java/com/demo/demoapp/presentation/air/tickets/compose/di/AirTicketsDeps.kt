package com.demo.demoapp.presentation.air.tickets.compose.di

import android.app.Application
import android.content.Context
import coil.ImageLoader
import com.demo.demoapp.core.common.Dependencies
import com.demo.demoapp.domain.usecases.EnterFromUseCase
import com.demo.demoapp.domain.usecases.EnterToUseCase
import com.demo.demoapp.domain.usecases.GetConcertsUseCase
import com.demo.demoapp.domain.usecases.GetPreviouslyEnteredFromUseCase
import com.demo.demoapp.domain.usecases.GetToDestinationsUseCase
import com.demo.demoapp.domain.usecases.GetTownsUseCase

sealed interface AirTicketsDepsSealed {
    data object Unspecified : AirTicketsDepsSealed
    interface AirTicketsDeps : Dependencies, AirTicketsDepsSealed {
        val application : Application
        val context : Context
        val imageLoader : ImageLoader
        val enterFromUseCase : EnterFromUseCase
        val enterToUseCase : EnterToUseCase
        val getConcertsUseCase : GetConcertsUseCase
        val getPreviouslyEnteredFromUseCase : GetPreviouslyEnteredFromUseCase
        val getToDestinationsUseCase : GetToDestinationsUseCase
        val getTownsUseCase : GetTownsUseCase
//    @Dispatcher(AppDispatcher.Default)
//    val dispatcherDefault: CoroutineDispatcher
    }

}

