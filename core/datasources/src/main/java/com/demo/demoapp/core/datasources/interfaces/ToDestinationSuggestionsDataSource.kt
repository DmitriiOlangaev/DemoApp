package com.demo.demoapp.core.datasources.interfaces

import com.demo.demoapp.core.model.ToDestinationSuggestion

interface ToDestinationSuggestionsDataSource {
    suspend fun getToDestinationSuggestion(): List<ToDestinationSuggestion>
}