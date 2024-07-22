package com.demo.demoapp.data.datastore

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class PreferencesDataStore @Inject constructor(
    private val dataStore: DataStore<UserPreferences>,
) : PreferencesDataSource {

    override val data: Flow<com.demo.demoapp.data.model.UserPreferences> =
        dataStore.data.map { userPreferences ->
            com.demo.demoapp.data.model.UserPreferences(
                userPreferences.fromList,
                userPreferences.toList
            )
        }

    override suspend fun updateUserPreferences(userPreferences: com.demo.demoapp.data.model.UserPreferences) {
        dataStore.updateData {
            it.copy {
                from.clear()
                from.addAll(userPreferences.enteredFrom)
                to.clear()
                to.addAll(userPreferences.enteredTo)
            }
        }
    }

    override suspend fun addEnteredFromDestination(destination: String) {
        dataStore.updateData {
            it.copy {
                from.toMutableList().apply {
                    addDestination(this, destination)
                    from.clear()
                    from.addAll(this)
                }
            }
        }
    }

    override suspend fun addEnteredToDestination(destination: String) {
        dataStore.updateData {
            it.copy {
                to.toMutableList().apply {
                    addDestination(this, destination)
                    to.clear()
                    to.addAll(this)
                }
            }
        }
    }

    private fun addDestination(list: MutableList<String>, destination: String) {
        list.remove(destination)
        list.add(destination)
        for (i in 0..<list.size - DESTINATIONS_LIST_MAX_SIZE) {
            list.removeFirstOrNull()
        }
    }


    companion object {
        private const val DESTINATIONS_LIST_MAX_SIZE = 5
    }
}