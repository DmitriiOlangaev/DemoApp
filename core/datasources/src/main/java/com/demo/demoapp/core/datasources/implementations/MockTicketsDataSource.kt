package com.demo.demoapp.core.datasources.implementations

import com.demo.demoapp.core.common.di.AppDispatcher
import com.demo.demoapp.core.common.di.Dispatcher
import com.demo.demoapp.core.datasources.interfaces.TicketsDataSource
import com.demo.demoapp.core.model.Ticket
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

internal class MockTicketsDataSource @Inject constructor(@Dispatcher(AppDispatcher.Default) private val defaultDispatcher: CoroutineDispatcher) :
    TicketsDataSource {
    private val mockString = "[\n" +
            "    {\n" +
            "      \"id\": 100,\n" +
            "      \"badge\": \"Самый удобный\",\n" +
            "      \"price\": {\n" +
            "        \"value\": 1999\n" +
            "      },\n" +
            "      \"provider_name\": \"На сайте Купибилет\",\n" +
            "      \"company\": \"Якутия\",\n" +
            "      \"departure\": {\n" +
            "        \"town\": \"Москва\",\n" +
            "        \"date\": \"2024-02-23T03:15:00\",\n" +
            "        \"airport\": \"VKO\"\n" +
            "      },\n" +
            "      \"arrival\": {\n" +
            "        \"town\": \"Сочи\",\n" +
            "        \"date\": \"2024-02-23T07:10:00\",\n" +
            "        \"airport\": \"AER\"\n" +
            "      },\n" +
            "      \"has_transfer\": false,\n" +
            "      \"has_visa_transfer\": false,\n" +
            "      \"luggage\": {\n" +
            "        \"has_luggage\": false,\n" +
            "        \"price\": {\n" +
            "          \"value\": 1082\n" +
            "        }\n" +
            "      },\n" +
            "      \"hand_luggage\": {\n" +
            "        \"has_hand_luggage\": true,\n" +
            "        \"size\": \"55x20x40\"\n" +
            "      },\n" +
            "      \"is_returnable\": false,\n" +
            "      \"is_exchangable\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 101,\n" +
            "      \"price\": {\n" +
            "        \"value\": 9999\n" +
            "      },\n" +
            "      \"provider_name\": \"На сайте Победа\",\n" +
            "      \"company\": \"Победа\",\n" +
            "      \"departure\": {\n" +
            "        \"town\": \"Москва\",\n" +
            "        \"date\": \"2024-02-23T04:00:00\",\n" +
            "        \"airport\": \"VKO\"\n" +
            "      },\n" +
            "      \"arrival\": {\n" +
            "        \"town\": \"Сочи\",\n" +
            "        \"date\": \"2024-02-23T08:30:00\",\n" +
            "        \"airport\": \"AER\"\n" +
            "      },\n" +
            "      \"has_transfer\": false,\n" +
            "      \"has_visa_transfer\": false,\n" +
            "      \"luggage\": {\n" +
            "        \"has_luggage\": false,\n" +
            "        \"price\": {\n" +
            "          \"value\": 1602\n" +
            "        }\n" +
            "      },\n" +
            "      \"hand_luggage\": {\n" +
            "        \"has_hand_luggage\": true,\n" +
            "        \"size\": \"36x30x27\"\n" +
            "      },\n" +
            "      \"is_returnable\": false,\n" +
            "      \"is_exchangable\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 102,\n" +
            "      \"price\": {\n" +
            "        \"value\": 8500\n" +
            "      },\n" +
            "      \"provider_name\": \"На сайте Turkish Airlines\",\n" +
            "      \"company\": \"Турецкие авиалинии\",\n" +
            "      \"departure\": {\n" +
            "        \"town\": \"Москва\",\n" +
            "        \"date\": \"2024-02-23T15:00:00\",\n" +
            "        \"airport\": \"VKO\"\n" +
            "      },\n" +
            "      \"arrival\": {\n" +
            "        \"town\": \"Сочи\",\n" +
            "        \"date\": \"2024-02-23T18:40:00\",\n" +
            "        \"airport\": \"AER\"\n" +
            "      },\n" +
            "      \"has_transfer\": false,\n" +
            "      \"has_visa_transfer\": false,\n" +
            "      \"luggage\": {\n" +
            "        \"has_luggage\": true\n" +
            "      },\n" +
            "      \"hand_luggage\": {\n" +
            "        \"has_hand_luggage\": true,\n" +
            "        \"size\": \"55x20x40\"\n" +
            "      },\n" +
            "      \"is_returnable\": false,\n" +
            "      \"is_exchangable\": false\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 103,\n" +
            "      \"badge\": \"Рекомендуемый\",\n" +
            "      \"price\": {\n" +
            "        \"value\": 8086\n" +
            "      },\n" +
            "      \"provider_name\": \"На сайте Уральские авиалинии\",\n" +
            "      \"company\": \"Уральские авиалинии\",\n" +
            "      \"departure\": {\n" +
            "        \"town\": \"Москва\",\n" +
            "        \"date\": \"2024-02-23T11:30:00\",\n" +
            "        \"airport\": \"VKO\"\n" +
            "      },\n" +
            "      \"arrival\": {\n" +
            "        \"town\": \"Сочи\",\n" +
            "        \"date\": \"2024-02-23T15:00:00\",\n" +
            "        \"airport\": \"AER\"\n" +
            "      },\n" +
            "      \"has_transfer\": false,\n" +
            "      \"has_visa_transfer\": false,\n" +
            "      \"luggage\": {\n" +
            "        \"has_luggage\": false,\n" +
            "        \"price\": {\n" +
            "          \"value\": 1570\n" +
            "        }\n" +
            "      },\n" +
            "      \"hand_luggage\": {\n" +
            "        \"has_hand_luggage\": true,\n" +
            "        \"size\": \"55x20x40\"\n" +
            "      },\n" +
            "      \"is_returnable\": true,\n" +
            "      \"is_exchangable\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 104,\n" +
            "      \"price\": {\n" +
            "        \"value\": 11560\n" +
            "      },\n" +
            "      \"provider_name\": \"На сайте Aeroflot\",\n" +
            "      \"company\": \"Аэрофлот\",\n" +
            "      \"departure\": {\n" +
            "        \"town\": \"Москва\",\n" +
            "        \"date\": \"2024-02-23T11:50:00\",\n" +
            "        \"airport\": \"VKO\"\n" +
            "      },\n" +
            "      \"arrival\": {\n" +
            "        \"town\": \"Сочи\",\n" +
            "        \"date\": \"2024-02-23T15:20:00\",\n" +
            "        \"airport\": \"AER\"\n" +
            "      },\n" +
            "      \"has_transfer\": true,\n" +
            "      \"has_visa_transfer\": false,\n" +
            "      \"luggage\": {\n" +
            "        \"has_luggage\": false,\n" +
            "        \"price\": {\n" +
            "          \"value\": 999\n" +
            "        }\n" +
            "      },\n" +
            "      \"hand_luggage\": {\n" +
            "        \"has_hand_luggage\": true,\n" +
            "        \"size\": \"55x20x40\"\n" +
            "      },\n" +
            "      \"is_returnable\": false,\n" +
            "      \"is_exchangable\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 105,\n" +
            "      \"price\": {\n" +
            "        \"value\": 15600\n" +
            "      },\n" +
            "      \"provider_name\": \"На сайте Aeroflot\",\n" +
            "      \"company\": \"Аэрофлот\",\n" +
            "      \"departure\": {\n" +
            "        \"town\": \"Москва\",\n" +
            "        \"date\": \"2024-02-23T13:50:00\",\n" +
            "        \"airport\": \"VKO\"\n" +
            "      },\n" +
            "      \"arrival\": {\n" +
            "        \"town\": \"Сочи\",\n" +
            "        \"date\": \"2024-02-23T17:20:00\",\n" +
            "        \"airport\": \"AER\"\n" +
            "      },\n" +
            "      \"has_transfer\": true,\n" +
            "      \"has_visa_transfer\": true,\n" +
            "      \"luggage\": {\n" +
            "        \"has_luggage\": false,\n" +
            "        \"price\": {\n" +
            "          \"value\": 1999\n" +
            "        }\n" +
            "      },\n" +
            "      \"hand_luggage\": {\n" +
            "        \"has_hand_luggage\": true,\n" +
            "        \"size\": \"55x20x40\"\n" +
            "      },\n" +
            "      \"is_returnable\": true,\n" +
            "      \"is_exchangable\": true\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 106,\n" +
            "      \"price\": {\n" +
            "        \"value\": 9500\n" +
            "      },\n" +
            "      \"provider_name\": \"На сайте Aeroflot\",\n" +
            "      \"company\": \"Аэрофлот\",\n" +
            "      \"departure\": {\n" +
            "        \"town\": \"Москва\",\n" +
            "        \"date\": \"2024-02-23T21:00:00\",\n" +
            "        \"airport\": \"VKO\"\n" +
            "      },\n" +
            "      \"arrival\": {\n" +
            "        \"town\": \"Сочи\",\n" +
            "        \"date\": \"2024-02-24T00:20:00\",\n" +
            "        \"airport\": \"AER\"\n" +
            "      },\n" +
            "      \"has_transfer\": false,\n" +
            "      \"has_visa_transfer\": false,\n" +
            "      \"luggage\": {\n" +
            "        \"has_luggage\": false,\n" +
            "        \"price\": {\n" +
            "          \"value\": 5999\n" +
            "        }\n" +
            "      },\n" +
            "      \"hand_luggage\": {\n" +
            "        \"has_hand_luggage\": false\n" +
            "      },\n" +
            "      \"is_returnable\": false,\n" +
            "      \"is_exchangable\": false\n" +
            "    }\n" +
            "  ]"

    override suspend fun getTickets(from: String, to: String): List<Ticket> =
        withContext(defaultDispatcher) { Json.decodeFromString(mockString) }
}