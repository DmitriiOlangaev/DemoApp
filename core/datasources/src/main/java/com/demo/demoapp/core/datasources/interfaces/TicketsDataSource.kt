package com.demo.demoapp.core.datasources.interfaces

import com.demo.demoapp.core.model.Ticket

interface TicketsDataSource {
    suspend fun getTickets(from: String, to: String): List<Ticket>

}