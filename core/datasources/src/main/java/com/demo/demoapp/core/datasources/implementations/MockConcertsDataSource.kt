package com.demo.demoapp.core.datasources.implementations

import com.demo.demoapp.core.common.di.AppDispatcher
import com.demo.demoapp.core.common.di.Dispatcher
import com.demo.demoapp.core.datasources.interfaces.ConcertsDataSource
import com.demo.demoapp.core.model.Concert
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

internal class MockConcertsDataSource @Inject constructor(@Dispatcher(AppDispatcher.Default) private val defaultDispatcher: CoroutineDispatcher) :
    ConcertsDataSource {
    private val mockString = "[\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"title\": \"Die Antwoord\",\n" +
            "      \"town\": \"Будапешт\",\n" +
            "      \"price\": {\n" +
            "        \"value\": 5000\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 2,\n" +
            "      \"title\": \"Socrat&Lera\",\n" +
            "      \"town\": \"Санкт-Петербург\",\n" +
            "      \"price\": {\n" +
            "        \"value\": 1999\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 3,\n" +
            "      \"title\": \"Лампабикт\",\n" +
            "      \"town\": \"Москва\",\n" +
            "      \"price\": {\n" +
            "        \"value\": 2390\n" +
            "      }\n" +
            "    }\n" +
            "  ]"

    override suspend fun getConcerts(): List<Concert> =
        withContext(defaultDispatcher) {
            Json.decodeFromString<List<Concert>>(mockString)
        }
}