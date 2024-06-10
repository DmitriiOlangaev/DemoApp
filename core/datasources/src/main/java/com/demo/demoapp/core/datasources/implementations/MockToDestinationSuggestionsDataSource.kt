package com.demo.demoapp.core.datasources.implementations

import com.demo.demoapp.core.datasources.interfaces.ToDestinationSuggestionsDataSource
import com.demo.demoapp.core.model.ToDestinationSuggestion
import javax.inject.Inject

internal class MockToDestinationSuggestionsDataSource @Inject constructor() :
    ToDestinationSuggestionsDataSource {
    override suspend fun getToDestinationSuggestion(): List<ToDestinationSuggestion> {
        return listOf(
            ToDestinationSuggestion(4, "Стамбул", "Популярное направление"),
            ToDestinationSuggestion(5, "Сочи", "Популярное направление"),
            ToDestinationSuggestion(6, "Пхукет", "Популярное направление")
        )
    }
}