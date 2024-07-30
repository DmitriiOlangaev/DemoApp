package com.demo.demoapp.data.repositories.implementations

import com.demo.demoapp.data.model.Concert
import com.demo.demoapp.data.model.Price
import com.demo.demoapp.data.network.NetworkDataSource
import com.demo.demoapp.data.repositories.interfaces.ConcertsRepository
import com.demo.demoapp.data.repositories.suspendRunCatching
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class MockConcertsRepository @Inject constructor(private val networkDataSource: NetworkDataSource) :
    ConcertsRepository {
    override fun getConcerts(): Flow<List<Concert>> = flow {
        emit(suspendRunCatching { networkDataSource.getConcerts() }.let {
            if (it.isSuccess) {
                it.getOrThrow()
            } else {
                delay(3000)
                listOf(
                    Concert(id = 1, title = "Группа 1", town = "Москва", price = Price(3228)),
                    Concert(id = 2, title = "Группа 2", town = "Санкт-Петербург", price = Price(0)),
                    Concert(id = 3, title = "Группа 3", town = "Казань", price = Price(993))
                )
            }
        })
    }
}