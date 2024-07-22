package com.demo.demoapp.data.network

import com.demo.demoapp.data.model.Concert
import com.demo.demoapp.data.model.Ticket
import com.demo.demoapp.data.model.TicketOffer
import java.util.Date

interface NetworkDataSource {
    suspend fun getConcerts(): List<Concert>

    suspend fun getTowns(): List<String>

    suspend fun getTicketsOffers(from: String, to: String): List<TicketOffer>

    suspend fun getTickets(
        from: String,
        to: String,
        date: Date,
        backDate: Date? = null
    ): List<Ticket>


}