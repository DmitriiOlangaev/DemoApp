package com.demo.demoapp.core.repositories.implementations

import com.demo.demoapp.core.datasources.di.DataSource
import com.demo.demoapp.core.datasources.di.DataSourceType
import com.demo.demoapp.core.datasources.interfaces.TownsDataSource
import com.demo.demoapp.core.repositories.interfaces.TownsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class MockTownsRepository @Inject constructor(@DataSource(DataSourceType.MOCK) private val townsDataSource: TownsDataSource) :
    TownsRepository {
    override fun getTowns(): Flow<List<String>> = flow { emit(townsDataSource.getTowns()) }
}