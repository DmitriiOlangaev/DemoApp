package com.demo.demoapp.data.network.di

import javax.inject.Qualifier

@Qualifier
annotation class Ui(val uiType: UiType)


enum class UiType {
    VIEW, COMPOSE
}