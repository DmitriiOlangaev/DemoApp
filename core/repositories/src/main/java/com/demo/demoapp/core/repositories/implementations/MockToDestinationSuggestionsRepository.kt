package com.demo.demoapp.core.repositories.implementations

import com.demo.demoapp.core.datasources.di.DataSource
import com.demo.demoapp.core.datasources.di.DataSourceType
import com.demo.demoapp.core.datasources.interfaces.ToDestinationSuggestionsDataSource
import com.demo.demoapp.core.model.ToDestinationSuggestion
import com.demo.demoapp.core.repositories.interfaces.ToDestinationSuggestionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class MockToDestinationSuggestionsRepository @Inject constructor(@DataSource(DataSourceType.MOCK) private val toDestinationSuggestionsDataSource: ToDestinationSuggestionsDataSource) :
    ToDestinationSuggestionsRepository {
    override fun getToDestinationSuggestions(): Flow<List<ToDestinationSuggestion>> =
        flow { emit(toDestinationSuggestionsDataSource.getToDestinationSuggestion()) }
}