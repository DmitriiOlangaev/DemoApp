package com.demo.demoapp.core.repositories.interfaces

import com.demo.demoapp.core.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    fun getUserData(): Flow<UserData>

    suspend fun setUserData(userData: UserData)

    suspend fun setFrom(from: String)

    suspend fun setTo(to: String)
}