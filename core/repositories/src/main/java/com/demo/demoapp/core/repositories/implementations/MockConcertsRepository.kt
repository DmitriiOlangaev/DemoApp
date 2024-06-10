package com.demo.demoapp.core.repositories.implementations

import com.demo.demoapp.core.datasources.di.DataSource
import com.demo.demoapp.core.datasources.di.DataSourceType
import com.demo.demoapp.core.datasources.interfaces.ConcertsDataSource
import com.demo.demoapp.core.model.Concert
import com.demo.demoapp.core.model.Price
import com.demo.demoapp.core.repositories.interfaces.ConcertsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.random.Random

internal class MockConcertsRepository @Inject constructor(@DataSource(DataSourceType.MOCK) private val concertsDataSource: ConcertsDataSource) :
    ConcertsRepository {
    private val random = Random(123)
    override fun getConcerts(): Flow<List<Concert>> = flow {
        emit(concertsDataSource.getConcerts())
        var prev = concertsDataSource.getConcerts()
        while (prev.size < 1000 - 7) {
            delay(200)
            prev = prev + Concert(
                random.nextInt(1, 4),
                "Серега Пират",
                "Владивосток",
                Price(random.nextInt(0, 99))
            )
            emit(prev)
        }
    }
}