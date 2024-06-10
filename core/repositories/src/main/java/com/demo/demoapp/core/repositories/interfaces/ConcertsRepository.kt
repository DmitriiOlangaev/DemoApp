package com.demo.demoapp.core.repositories.interfaces

import com.demo.demoapp.core.model.Concert
import kotlinx.coroutines.flow.Flow

interface ConcertsRepository {
    fun getConcerts(): Flow<List<Concert>>
}