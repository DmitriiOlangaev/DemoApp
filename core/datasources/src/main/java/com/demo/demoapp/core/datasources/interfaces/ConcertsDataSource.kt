package com.demo.demoapp.core.datasources.interfaces

import com.demo.demoapp.core.model.Concert

interface ConcertsDataSource {
    suspend fun getConcerts(): List<Concert>

}