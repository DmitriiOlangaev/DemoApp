package com.demo.demoapp.view.di

import com.demo.demoapp.core.common.di.ActivityScope
import com.demo.demoapp.feature.airTickets.di.AirTicketsDeps
import com.demo.demoapp.view.ui.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [AirTicketsDepsBindingsModule::class])
internal interface MainActivityComponent : AirTicketsDeps {
    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance mainActivity: MainActivity): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}