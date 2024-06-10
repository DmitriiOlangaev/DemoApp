package com.demo.demoapp.core.datasources.di

import javax.inject.Qualifier

@Qualifier
annotation class DataSource(val dataSourceType: DataSourceType)

enum class DataSourceType {
    LOCAL, REMOTE, MOCK
}