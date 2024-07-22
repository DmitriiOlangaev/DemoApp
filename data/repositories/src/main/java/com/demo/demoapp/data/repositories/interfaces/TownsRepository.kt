package com.demo.demoapp.data.repositories.interfaces

import kotlinx.coroutines.flow.Flow

interface TownsRepository {
    fun getTowns(): Flow<List<String>>
}