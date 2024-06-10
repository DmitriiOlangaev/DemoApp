package com.demo.demoapp.core.repositories.implementations

import com.demo.demoapp.core.datasources.di.DataSource
import com.demo.demoapp.core.datasources.di.DataSourceType
import com.demo.demoapp.core.datasources.interfaces.TicketsDataSource
import com.demo.demoapp.core.model.Ticket
import com.demo.demoapp.core.repositories.interfaces.TicketsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class MockTicketsRepository @Inject constructor(@DataSource(DataSourceType.MOCK) private val mockTicketsDataSource: TicketsDataSource) :
    TicketsRepository {
    override fun getTickets(from: String, to: String): Flow<List<Ticket>> =
        flow { emit(mockTicketsDataSource.getTickets(from, to)) }
}