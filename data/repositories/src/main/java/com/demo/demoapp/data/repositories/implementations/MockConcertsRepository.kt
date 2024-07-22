package com.demo.demoapp.data.repositories.implementations

import com.demo.demoapp.data.model.Concert
import com.demo.demoapp.data.network.NetworkDataSource
import com.demo.demoapp.data.repositories.interfaces.ConcertsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class MockConcertsRepository @Inject constructor(private val networkDataSource: NetworkDataSource) :
    ConcertsRepository {
    override fun getConcerts(): Flow<List<Concert>> = flow {
        emit(networkDataSource.getConcerts())
    }
}