package com.demo.demoapp.core.datasources.di

import com.demo.demoapp.core.common.di.ApplicationScope
import com.demo.demoapp.core.datasources.implementations.MockConcertsDataSource
import com.demo.demoapp.core.datasources.implementations.MockTicketsDataSource
import com.demo.demoapp.core.datasources.implementations.MockTicketsOffersDataSource
import com.demo.demoapp.core.datasources.implementations.MockToDestinationSuggestionsDataSource
import com.demo.demoapp.core.datasources.implementations.MockTownDataSource
import com.demo.demoapp.core.datasources.interfaces.ConcertsDataSource
import com.demo.demoapp.core.datasources.interfaces.TicketsDataSource
import com.demo.demoapp.core.datasources.interfaces.TicketsOffersDataSource
import com.demo.demoapp.core.datasources.interfaces.ToDestinationSuggestionsDataSource
import com.demo.demoapp.core.datasources.interfaces.TownsDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourcesModule {

    @Binds
    @DataSource(DataSourceType.MOCK)
    @ApplicationScope
    internal abstract fun concertsDataSource(mockConcertsDataSource: MockConcertsDataSource): ConcertsDataSource

    @Binds
    @DataSource(DataSourceType.MOCK)
    @ApplicationScope
    internal abstract fun ticketsDataSource(mockTicketsDataSource: MockTicketsDataSource): TicketsDataSource

    @Binds
    @DataSource(DataSourceType.MOCK)
    @ApplicationScope
    internal abstract fun ticketsOffersDataSource(mockTicketsOffersDataSource: MockTicketsOffersDataSource): TicketsOffersDataSource

    @Binds
    @DataSource(DataSourceType.MOCK)
    @ApplicationScope
    internal abstract fun toDestinationSuggestionsDataSource(mockToDestinationSuggestionsDataSource: MockToDestinationSuggestionsDataSource): ToDestinationSuggestionsDataSource

    @Binds
    @DataSource(DataSourceType.MOCK)
    @ApplicationScope
    internal abstract fun townsDataSource(mockTownDataSource: MockTownDataSource): TownsDataSource

}