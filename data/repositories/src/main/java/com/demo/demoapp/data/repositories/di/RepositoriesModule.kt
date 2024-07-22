package com.demo.demoapp.data.repositories.di

import com.demo.demoapp.core.common.di.ApplicationScope
import com.demo.demoapp.data.repositories.implementations.MockConcertsRepository
import com.demo.demoapp.data.repositories.implementations.MockTownsRepository
import com.demo.demoapp.data.repositories.interfaces.ConcertsRepository
import com.demo.demoapp.data.repositories.interfaces.TownsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoriesModule {
    @Binds
    @ApplicationScope
    internal abstract fun concertsRepository(mockConcertsRepository: MockConcertsRepository): ConcertsRepository

    @Binds
    @ApplicationScope
    internal abstract fun townsRepository(mockTownsRepository: MockTownsRepository): TownsRepository

}