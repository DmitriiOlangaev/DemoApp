package com.demo.demoapp.domain.usecases

import com.demo.demoapp.data.datastore.PreferencesDataSource
import javax.inject.Inject

class EnterToUseCase @Inject constructor(private val preferencesDataSource: PreferencesDataSource) {
    suspend operator fun invoke(destination: String) {
        preferencesDataSource.addEnteredToDestination(destination)
    }
}