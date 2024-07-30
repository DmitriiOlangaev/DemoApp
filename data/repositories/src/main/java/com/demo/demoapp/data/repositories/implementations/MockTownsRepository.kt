package com.demo.demoapp.data.repositories.implementations

import com.demo.demoapp.data.network.NetworkDataSource
import com.demo.demoapp.data.repositories.interfaces.TownsRepository
import com.demo.demoapp.data.repositories.suspendRunCatching
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class MockTownsRepository @Inject constructor(private val networkDataSource: NetworkDataSource) :
    TownsRepository {

    private val towns: List<String> = listOf(
        "Астрахань",
        "Брянск",
        "Волгоград",
        "Геленджик",
        "Долгопрудный",
        "Екатеринбург",
        "Железногорск",
        "Зеленоград",
        "Иркутск"
    )

    override fun getTowns(): Flow<List<String>> = flow {
        emit(suspendRunCatching { networkDataSource.getTowns() }.let {
            if (it.isSuccess) {
                it.getOrThrow()
            } else {
                delay(4000)
                towns
            }
        }.map { it.trim() })
    }
}