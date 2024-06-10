package com.demo.demoapp.core.datasources.implementations

import com.demo.demoapp.core.common.di.AppDispatcher
import com.demo.demoapp.core.common.di.Dispatcher
import com.demo.demoapp.core.datasources.interfaces.TicketsOffersDataSource
import com.demo.demoapp.core.model.TicketOffer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

internal class MockTicketsOffersDataSource @Inject constructor(@Dispatcher(AppDispatcher.Default) private val defaultDispatcher: CoroutineDispatcher) :
    TicketsOffersDataSource {
    private val mockString = "[\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"title\": \"Уральские авиалинии\",\n" +
            "      \"time_range\": [\n" +
            "        \"07:00\",\n" +
            "        \"09:10\",\n" +
            "        \"10:00\",\n" +
            "        \"11:30\",\n" +
            "        \"14:15\",\n" +
            "        \"19:10\",\n" +
            "        \"21:00\",\n" +
            "        \"23:30\"\n" +
            "      ],\n" +
            "      \"price\": {\n" +
            "        \"value\": 3999\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 10,\n" +
            "      \"title\": \"Победа\",\n" +
            "      \"time_range\": [\n" +
            "        \"09:10\",\n" +
            "        \"21:00\"\n" +
            "      ],\n" +
            "      \"price\": {\n" +
            "        \"value\": 4999\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 30,\n" +
            "      \"title\": \"NordStar\",\n" +
            "      \"time_range\": [\n" +
            "        \"07:00\"\n" +
            "      ],\n" +
            "      \"price\": {\n" +
            "        \"value\": 2390\n" +
            "      }\n" +
            "    }\n" +
            "  ]"

    override suspend fun getTicketsOffers(from: String, to: String): List<TicketOffer> =
        withContext(defaultDispatcher) { Json.decodeFromString<List<TicketOffer>>(mockString) }
}