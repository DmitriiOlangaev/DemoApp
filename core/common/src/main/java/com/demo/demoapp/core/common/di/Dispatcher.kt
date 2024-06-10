package com.demo.demoapp.core.common.di

import javax.inject.Qualifier

@Qualifier
annotation class Dispatcher(val appDispatcher: AppDispatcher)

enum class AppDispatcher {
    Default,
    IO
}
