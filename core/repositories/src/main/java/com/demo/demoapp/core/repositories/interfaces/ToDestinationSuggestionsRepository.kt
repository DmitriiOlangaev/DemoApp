package com.demo.demoapp.core.repositories.interfaces

import com.demo.demoapp.core.model.ToDestinationSuggestion
import kotlinx.coroutines.flow.Flow

interface ToDestinationSuggestionsRepository {
    fun getToDestinationSuggestions(): Flow<List<ToDestinationSuggestion>>
}