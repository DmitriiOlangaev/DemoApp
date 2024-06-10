package com.demo.demoapp.core.repositories.interfaces

import kotlinx.coroutines.flow.Flow

interface TownsRepository {
    fun getTowns(): Flow<List<String>>
}