package com.demo.demoapp.core.network.di

import javax.inject.Qualifier

@Qualifier
annotation class Ui(val uiType: UiType)


enum class UiType {
    VIEW, COMPOSE
}