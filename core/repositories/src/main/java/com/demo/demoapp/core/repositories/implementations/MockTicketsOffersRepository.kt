package com.demo.demoapp.core.repositories.implementations

import com.demo.demoapp.core.datasources.di.DataSource
import com.demo.demoapp.core.datasources.di.DataSourceType
import com.demo.demoapp.core.datasources.interfaces.TicketsOffersDataSource
import com.demo.demoapp.core.model.TicketOffer
import com.demo.demoapp.core.repositories.interfaces.TicketsOffersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class MockTicketsOffersRepository @Inject constructor(@DataSource(DataSourceType.MOCK) private val ticketsOffersDataSource: TicketsOffersDataSource) :
    TicketsOffersRepository {
    override fun getTicketsOffers(from: String, to: String): Flow<List<TicketOffer>> =
        flow { emit(ticketsOffersDataSource.getTicketsOffers(from, to)) }
}