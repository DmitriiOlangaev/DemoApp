package com.demo.demoapp.feature.airTickets.di

import com.demo.demoapp.core.common.Dependencies
import com.demo.demoapp.core.repositories.interfaces.ConcertsRepository
import com.demo.demoapp.core.repositories.interfaces.ImagesRepository
import com.demo.demoapp.core.repositories.interfaces.TicketsOffersRepository
import com.demo.demoapp.core.repositories.interfaces.TicketsRepository
import com.demo.demoapp.core.repositories.interfaces.ToDestinationSuggestionsRepository
import com.demo.demoapp.core.repositories.interfaces.TownsRepository
import com.demo.demoapp.core.repositories.interfaces.UserDataRepository

interface AirTicketsDeps : Dependencies {
    val concertsRepository: ConcertsRepository
    val ticketsRepository: TicketsRepository
    val ticketsOffersRepository: TicketsOffersRepository
    val toDestinationSuggestionsRepository: ToDestinationSuggestionsRepository
    val imagesRepository: ImagesRepository
    val townsRepository: TownsRepository
    val userDataRepository: UserDataRepository
}