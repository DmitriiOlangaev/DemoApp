package com.demo.demoapp.feature.airTickets.di

import com.demo.demoapp.core.common.Dependencies
import com.demo.demoapp.core.common.DependenciesKey
import com.demo.demoapp.core.common.di.FragmentScope
import com.demo.demoapp.feature.airTickets.ui.fragments.AirTicketsNavHostFragment
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@Component(dependencies = [AirTicketsDeps::class], modules = [AirTicketsNavHostFragmentModule::class])
@FragmentScope
internal interface AirTicketsNavHostFragmentComponent : EnterFragmentDeps, ChoosingDestsFragmentDeps {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance airTicketsNavHostFragment: AirTicketsNavHostFragment, deps: AirTicketsDeps) : AirTicketsNavHostFragmentComponent
    }

    fun inject(airTicketsNavHostFragment: AirTicketsNavHostFragment)
}

@Module
internal interface AirTicketsNavHostFragmentModule {
    @Binds
    @IntoMap
    @DependenciesKey(EnterFragmentDeps::class)
    fun bindEnterFragmentDeps(airTicketsNavHostFragmentComponent: AirTicketsNavHostFragmentComponent) : Dependencies

    @Binds
    @IntoMap
    @DependenciesKey(ChoosingDestsFragmentDeps::class)
    fun bindChoosingDestsFragmentDeps(airTicketsNavHostFragmentComponent: AirTicketsNavHostFragmentComponent) : Dependencies
}