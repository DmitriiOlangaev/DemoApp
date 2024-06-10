package com.demo.demoapp.core.repositories.di

import com.demo.demoapp.core.common.di.ApplicationScope
import com.demo.demoapp.core.repositories.implementations.MockConcertsRepository
import com.demo.demoapp.core.repositories.implementations.MockImagesRepository
import com.demo.demoapp.core.repositories.implementations.MockTicketsOffersRepository
import com.demo.demoapp.core.repositories.implementations.MockTicketsRepository
import com.demo.demoapp.core.repositories.implementations.MockToDestinationSuggestionsRepository
import com.demo.demoapp.core.repositories.implementations.MockTownsRepository
import com.demo.demoapp.core.repositories.implementations.UserDataRepositoryImpl
import com.demo.demoapp.core.repositories.interfaces.ConcertsRepository
import com.demo.demoapp.core.repositories.interfaces.ImagesRepository
import com.demo.demoapp.core.repositories.interfaces.TicketsOffersRepository
import com.demo.demoapp.core.repositories.interfaces.TicketsRepository
import com.demo.demoapp.core.repositories.interfaces.ToDestinationSuggestionsRepository
import com.demo.demoapp.core.repositories.interfaces.TownsRepository
import com.demo.demoapp.core.repositories.interfaces.UserDataRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoriesModule {
    @Binds
    @ApplicationScope
    internal abstract fun concertsRepository(mockConcertsRepository: MockConcertsRepository): ConcertsRepository

    @Binds
    @ApplicationScope
    internal abstract fun ticketsRepository(mockTicketsRepository: MockTicketsRepository): TicketsRepository

    @Binds
    @ApplicationScope
    internal abstract fun ticketsOffersRepository(mockTicketsOffersRepository: MockTicketsOffersRepository): TicketsOffersRepository

    @Binds
    @ApplicationScope
    internal abstract fun toDestinationSuggestionsRepository(mockToDestinationSuggestionsRepository: MockToDestinationSuggestionsRepository): ToDestinationSuggestionsRepository

    @Binds
    @ApplicationScope
    internal abstract fun imagesRepository(mockImagesRepository: MockImagesRepository): ImagesRepository

    @Binds
    @ApplicationScope
    internal abstract fun townsRepository(mockTownsRepository: MockTownsRepository): TownsRepository

    @Binds
    @ApplicationScope
    internal abstract fun userDataRepository(userDataRepository: UserDataRepositoryImpl): UserDataRepository
}