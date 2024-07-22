package com.demo.demoapp.feature.airTickets.di

import androidx.recyclerview.widget.DiffUtil
import com.demo.demoapp.feature.airTickets.ui.fragments.ChoosingFromFragment
import com.demo.demoapp.feature.airTickets.ui.recyclerView.TownUiState
import com.demo.demoapp.feature.airTickets.ui.recyclerView.TownsDelegate
import com.demo.demoapp.feature.airTickets.ui.recyclerView.TownsLoadingDelegate
import com.demo.demoapp.feature.airTickets.viewModels.ChoosingFromFragmentViewModel
import com.demo.demoapp.feature.airTickets.viewModels.TownClickedSharedViewModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(dependencies = [ChoosingFromFragmentDeps::class], modules = [ChoosingFromFragmentModule::class])
internal interface ChoosingFromFragmentComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance choosingFromFragment: ChoosingFromFragment,
            @BindsInstance townsClickedSharedViewModel: TownClickedSharedViewModel,
            deps: ChoosingFromFragmentDeps
        ) : ChoosingFromFragmentComponent
    }

    fun viewModelFactory() : ChoosingFromFragmentViewModel.Factory
    fun inject(choosingFromFragment: ChoosingFromFragment)
}


@Module
internal interface ChoosingFromFragmentModule {
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