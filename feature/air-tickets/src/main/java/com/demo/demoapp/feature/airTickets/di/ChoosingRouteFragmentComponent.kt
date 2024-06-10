package com.demo.demoapp.feature.airTickets.di

import android.content.Context
import android.widget.ArrayAdapter
import com.demo.demoapp.core.common.di.FragmentScope
import com.demo.demoapp.feature.airTickets.ui.fragments.ChoosingRouteFragment
import com.demo.demoapp.feature.airTickets.ui.viewControllers.ChoosingRouteFragmentViewController
import com.demo.demoapp.feature.airTickets.viewModels.ChoosingRouteFragmentViewModel
import com.demo.demoapp.feature.airTickets.viewModels.ToDestinationSharedViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel

@Component(
    dependencies = [AirTicketsDeps::class],
    modules = [ChoosingRouteFragmentModule::class, SubcomponentsModule::class]
)
@FragmentScope
internal interface ChoosingRouteFragmentComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance choosingRouteFragment: ChoosingRouteFragment,
            @BindsInstance context: Context,
            deps: AirTicketsDeps
        ): ChoosingRouteFragmentComponent
    }

    fun searchStateInitFragmentComponentFactory(): SearchStateInitFragmentComponent.Factory

    fun searchStateChoosingToFragmentComponentFactory(): SearchStateChoosingToFragmentComponent.Factory

    fun searchStateDestinationsChosenFragmentComponentFactory(): SearchStateDestinationsChosenFragmentComponent.Factory

    fun choosingRouteFragmentViewController(): ChoosingRouteFragmentViewController.Factory

    fun choosingRouteFragmentViewModel(): ChoosingRouteFragmentViewModel.Factory

    fun toDestinationSharedViewModel(): ToDestinationSharedViewModel.Factory

    fun inject(choosingRouteFragment: ChoosingRouteFragment)
}

@Module
internal interface ChoosingRouteFragmentModule {
    companion object {
        @Provides
        fun coroutineScope(): CoroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

        @Provides
        fun toDestinationChannel(): Channel<String> = Channel()

        @Provides
        fun arrayAdapter(context: Context): ArrayAdapter<String> =
            ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, mutableListOf())
    }
}

@Module(subcomponents = [SearchStateInitFragmentComponent::class, SearchStateChoosingToFragmentComponent::class, SearchStateDestinationsChosenFragmentComponent::class])
internal object SubcomponentsModule
