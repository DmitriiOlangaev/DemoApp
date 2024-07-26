package com.demo.demoapp.data.repositories.di

import com.demo.demoapp.data.repositories.implementations.MockConcertsRepository
import com.demo.demoapp.data.repositories.implementations.MockTownsRepository
import com.demo.demoapp.data.repositories.interfaces.ConcertsRepository
import com.demo.demoapp.data.repositories.interfaces.TownsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {
    @Binds
    @Singleton
    internal abstract fun concertsRepository(mockConcertsRepository: MockConcertsRepository): ConcertsRepository

    @Binds
    @Singleton
    internal abstract fun townsRepository(mockTownsRepository: MockTownsRepository): TownsRepository

}