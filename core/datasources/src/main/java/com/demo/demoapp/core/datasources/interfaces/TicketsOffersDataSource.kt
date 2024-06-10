package com.demo.demoapp.core.datasources.interfaces

import com.demo.demoapp.core.model.TicketOffer

interface TicketsOffersDataSource {
    suspend fun getTicketsOffers(from: String, to: String): List<TicketOffer>

}