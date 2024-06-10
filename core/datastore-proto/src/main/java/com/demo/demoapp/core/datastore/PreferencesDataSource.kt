package com.demo.demoapp.core.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import com.demo.demoapp.core.model.UserData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesDataSource @Inject constructor(private val userDataPreferencesStore: DataStore<UserPreferences>) {
    val data = userDataPreferencesStore.data.map {
        UserData(it.from, it.to)
    }

    suspend fun setUserData(userData: UserData) {
        runCatching {
            userDataPreferencesStore.updateData {
                it.copy {
                    from = userData.enteredFromDest
                    to = userData.enteredToDest
                }
            }
        }.onFailure {
            Log.e("DemoApp", "Failed to update entered destinations", it)
        }
    }

    suspend fun setEnteredFrom(from: String) {
        runCatching {
            userDataPreferencesStore.updateData {
                it.copy {
                    this.from = from
                }
            }
        }.onFailure {
            Log.e("DemoApp", "Failed to update entered from", it)
        }
    }

    suspend fun setEnteredTo(to: String) {
        runCatching {
            userDataPreferencesStore.updateData {
                it.copy {
                    this.to = to
                }
            }
        }.onFailure {
            Log.e("DemoApp", "Failed to update entered to", it)
        }
    }
}