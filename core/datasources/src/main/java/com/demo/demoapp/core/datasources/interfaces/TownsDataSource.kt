package com.demo.demoapp.core.datasources.interfaces

interface TownsDataSource {
    suspend fun getTowns(): List<String>
}