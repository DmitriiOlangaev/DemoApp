package com.demo.demoapp.core.datasources.implementations

import com.demo.demoapp.core.datasources.interfaces.TownsDataSource
import javax.inject.Inject

internal class MockTownDataSource @Inject constructor() : TownsDataSource {
    override suspend fun getTowns(): List<String> =
        listOf(
            "Москва",
            "Париж",
            "Берлин",
            "Лондон",
            "Стамбул",
            "Пхукет",
            "Вашингтон",
            "Амстердам",
            "Санкт-Петербург",
            "Будапешт",
            "Сочи",
            "Мадрид",
            "Кишинев"
        )
}