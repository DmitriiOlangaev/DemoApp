package com.demo.demoapp.data.datastore

import com.demo.demoapp.data.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface PreferencesDataSource {
    val data: Flow<UserPreferences>

    suspend fun updateUserPreferences(userPreferences: UserPreferences)

    suspend fun addEnteredFromDestination(destination: String)

    suspend fun addEnteredToDestination(destination: String)
}