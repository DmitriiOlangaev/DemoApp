package com.demo.demoapp.core.network

import com.demo.demoapp.core.network.model.Concert
import com.demo.demoapp.core.network.model.Ticket
import com.demo.demoapp.core.network.model.TicketOffer
import com.demo.demoapp.core.network.model.ToDestinationSuggestion
import java.util.Date

interface NetworkDataSource {
    suspend fun getConcerts(): List<Concert>

    suspend fun getToDestinationSuggestions() : List<ToDestinationSuggestion>

    suspend fun getTicketsOffers(from: String, to: String): List<TicketOffer>

    suspend fun getTickets(from: String, to: String, date: Date, backDate: Date? = null): List<Ticket>


}