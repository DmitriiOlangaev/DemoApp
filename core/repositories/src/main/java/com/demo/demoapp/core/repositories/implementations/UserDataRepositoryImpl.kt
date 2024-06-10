package com.demo.demoapp.core.repositories.implementations

import com.demo.demoapp.core.datastore.PreferencesDataSource
import com.demo.demoapp.core.model.UserData
import com.demo.demoapp.core.repositories.interfaces.UserDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class UserDataRepositoryImpl @Inject constructor(private val preferencesDataSource: PreferencesDataSource) :
    UserDataRepository {
    override fun getUserData(): Flow<UserData> = preferencesDataSource.data

    override suspend fun setUserData(userData: UserData) =
        preferencesDataSource.setUserData(userData)

    override suspend fun setFrom(from: String) = preferencesDataSource.setEnteredFrom(from)

    override suspend fun setTo(to: String) = preferencesDataSource.setEnteredTo(to)
}