package com.demo.demoapp.view.di

import android.content.Context
import com.demo.demoapp.core.common.di.ActivityScope
import com.demo.demoapp.feature.airTickets.di.AirTicketsDeps
import com.demo.demoapp.view.ui.MainActivity
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainActivityModule::class, AirTicketsDepsBindingsModule::class])
internal interface MainActivityComponent : AirTicketsDeps {
    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance mainActivity: MainActivity): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}

@Module
internal interface MainActivityModule {
    @Binds
    fun getContext(mainActivity: MainActivity): Context
}