package com.demo.demoapp.core.repositories.interfaces

import com.demo.demoapp.core.model.Ticket
import kotlinx.coroutines.flow.Flow

interface TicketsRepository {
    fun getTickets(from: String, to: String): Flow<List<Ticket>>
}