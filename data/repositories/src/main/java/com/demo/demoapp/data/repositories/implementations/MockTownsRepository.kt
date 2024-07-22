package com.demo.demoapp.data.repositories.implementations

import com.demo.demoapp.data.network.NetworkDataSource
import com.demo.demoapp.data.repositories.interfaces.TownsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class MockTownsRepository @Inject constructor(private val networkDataSource: NetworkDataSource) :
    TownsRepository {
    override fun getTowns(): Flow<List<String>> = flow {
        emit(networkDataSource.getTowns().map { it.trim() })
    }
}