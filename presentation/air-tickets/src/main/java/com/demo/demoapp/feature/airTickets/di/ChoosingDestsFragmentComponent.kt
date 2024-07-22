package com.demo.demoapp.feature.airTickets.di

import com.demo.demoapp.core.common.Dependencies
import com.demo.demoapp.core.common.DependenciesKey
import com.demo.demoapp.core.common.di.FragmentScope
import com.demo.demoapp.feature.airTickets.ui.fragments.ChoosingDestsBottomSheet
import com.demo.demoapp.feature.airTickets.viewModels.ChoosingDestsFragmentViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@Component(dependencies = [ChoosingDestsFragmentDeps::class], modules = [ChoosingDestsFragmentModule::class])
@FragmentScope
internal interface ChoosingDestsFragmentComponent : ChoosingTownFragmentDeps, ChoosingFromFragmentDeps, ChoosingToFragmentDeps {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance choosingDestsBottomSheet: ChoosingDestsBottomSheet,
            deps: ChoosingDestsFragmentDeps
        ) : ChoosingDestsFragmentComponent
    }

    fun inject(choosingDestsBottomSheet: ChoosingDestsBottomSheet)

    fun viewModelFactory(): ChoosingDestsFragmentViewModel.Factory

}

@Module()
internal interface ChoosingDestsFragmentModule {
    @Binds
    @IntoMap
    @DependenciesKey(ChoosingTownFragmentDeps::class)
    fun bindChoosingTownFragmentDeps(choosingDestsFragmentComponent: ChoosingDestsFragmentComponent) : Dependencies

    @Binds
    @IntoMap
    @DependenciesKey(ChoosingFromFragmentDeps::class)
    fun bindChoosingFromFragmentDeps(choosingDestsFragmentComponent: ChoosingDestsFragmentComponent) : Dependencies

    @Binds
    @IntoMap
    @DependenciesKey(ChoosingToFragmentDeps::class)
    fun bindChoosingToFragmentDeps(choosingDestsFragmentComponent: ChoosingDestsFragmentComponent) : Dependencies
}