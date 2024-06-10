package com.demo.demoapp.core.repositories.interfaces

import com.demo.demoapp.core.model.TicketOffer
import kotlinx.coroutines.flow.Flow

interface TicketsOffersRepository {
    fun getTicketsOffers(from: String, to: String): Flow<List<TicketOffer>>
}