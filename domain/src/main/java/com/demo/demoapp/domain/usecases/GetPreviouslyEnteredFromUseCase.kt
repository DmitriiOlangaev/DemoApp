package com.demo.demoapp.domain.usecases

import com.demo.demoapp.data.datastore.PreferencesDataSource
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPreviouslyEnteredFromUseCase @Inject constructor(private val preferencesDataSource: PreferencesDataSource) {
    operator fun invoke() = preferencesDataSource.data.map { it.enteredFrom }
}