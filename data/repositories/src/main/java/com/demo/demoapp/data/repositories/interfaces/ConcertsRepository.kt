package com.demo.demoapp.data.repositories.interfaces

import com.demo.demoapp.data.model.Concert
import kotlinx.coroutines.flow.Flow

interface ConcertsRepository {
    fun getConcerts(): Flow<List<Concert>>
}