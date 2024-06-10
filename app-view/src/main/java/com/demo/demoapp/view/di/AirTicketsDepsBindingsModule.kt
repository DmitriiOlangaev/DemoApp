package com.demo.demoapp.view.di

import com.demo.demoapp.core.common.di.Dependencies
import com.demo.demoapp.core.common.di.DependenciesKey
import com.demo.demoapp.feature.airTickets.di.AirTicketsDeps
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface AirTicketsDepsBindingsModule {
    @Binds
    @IntoMap
    @DependenciesKey(AirTicketsDeps::class)
    fun bindAirTicketsDeps(mainActivityComponent: MainActivityComponent): Dependencies
}