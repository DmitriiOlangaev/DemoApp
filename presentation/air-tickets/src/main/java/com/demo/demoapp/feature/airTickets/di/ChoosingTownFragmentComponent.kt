package com.demo.demoapp.feature.airTickets.di

import androidx.recyclerview.widget.DiffUtil
import com.demo.demoapp.core.common.di.FragmentScope
import com.demo.demoapp.feature.airTickets.ui.fragments.ChoosingTownFragment
import com.demo.demoapp.feature.airTickets.ui.recyclerView.TownUiState
import com.demo.demoapp.feature.airTickets.ui.recyclerView.TownsDelegate
import com.demo.demoapp.feature.airTickets.ui.recyclerView.TownsLoadingDelegate
import com.demo.demoapp.feature.airTickets.viewModels.ChoosingTownFragmentViewModel
import com.demo.demoapp.feature.airTickets.viewModels.EnteredTownSharedViewModel
import com.demo.demoapp.feature.airTickets.viewModels.TownClickedSharedViewModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(dependencies = [ChoosingTownFragmentDeps::class], modules = [ChoosingTownFragmentModule::class])
@FragmentScope
internal interface ChoosingTownFragmentComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance choosingTownFragment: ChoosingTownFragment,
            @BindsInstance enteredTownSharedViewModel: EnteredTownSharedViewModel,
            @BindsInstance townsClickedSharedViewModel: TownClickedSharedViewModel,
            deps: ChoosingTownFragmentDeps
        ) : ChoosingTownFragmentComponent
    }
    fun viewModelFactory() : ChoosingTownFragmentViewModel.Factory
    fun inject(choosingTownFragment: ChoosingTownFragment)
}

@Module
internal interface ChoosingTownFragmentModule {
    companion object {
        @Provides
        fun townsAdapter(townsDelegate: TownsDelegate, townsLoadingDelegate: TownsLoadingDelegate) : AsyncListDifferDelegationAdapter<TownUiState> {
            val callback = object : DiffUtil.ItemCallback<TownUiState>() {
                override fun areItemsTheSame(
                    oldItem: TownUiState,
                    newItem: TownUiState
                ): Boolean = oldItem == newItem

                override fun areContentsTheSame(
                    oldItem: TownUiState,
                    newItem: TownUiState
                ): Boolean = oldItem == newItem
            }
            return object : AsyncListDifferDelegationAdapter<TownUiState>(callback) {
                init {
                    delegatesManager.addDelegate(townsDelegate)
                    delegatesManager.addDelegate(townsLoadingDelegate)
                }
            }
        }
    }
}