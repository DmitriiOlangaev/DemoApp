package com.demo.demoapp.feature.airTickets.di

import androidx.recyclerview.widget.DiffUtil
import com.demo.demoapp.feature.airTickets.ui.fragments.ChoosingToFragment
import com.demo.demoapp.feature.airTickets.ui.recyclerView.DestinationsSuggestionsDelegate
import com.demo.demoapp.feature.airTickets.ui.recyclerView.DestinationsSuggestionsLoadingDelegate
import com.demo.demoapp.feature.airTickets.ui.recyclerView.ToDestinationUiState
import com.demo.demoapp.feature.airTickets.viewModels.ChoosingToFragmentViewModel
import com.demo.demoapp.feature.airTickets.viewModels.TownClickedSharedViewModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    dependencies = [ChoosingToFragmentDeps::class],
    modules = [ChoosingToFragmentModule::class]
)
internal interface ChoosingToFragmentComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance choosingToFragment: ChoosingToFragment,
            @BindsInstance townsClickedSharedViewModel: TownClickedSharedViewModel,
            deps: ChoosingToFragmentDeps
        ): ChoosingToFragmentComponent
    }

    fun viewModelFactory(): ChoosingToFragmentViewModel.Factory
    fun inject(choosingToFragment: ChoosingToFragment)
}

@Module
internal interface ChoosingToFragmentModule {
    companion object {
        @Provides
        fun destinationsSuggestionsAdapter(
            destinationsSuggestionsDelegate: DestinationsSuggestionsDelegate,
            destinationsSuggestionsLoadingDelegate: DestinationsSuggestionsLoadingDelegate
        ): AsyncListDifferDelegationAdapter<ToDestinationUiState> {
            val callback = object : DiffUtil.ItemCallback<ToDestinationUiState>() {
                override fun areItemsTheSame(
                    oldItem: ToDestinationUiState,
                    newItem: ToDestinationUiState
                ): Boolean = oldItem == newItem

                override fun areContentsTheSame(
                    oldItem: ToDestinationUiState,
                    newItem: ToDestinationUiState
                ): Boolean = oldItem == newItem
            }
            return object : AsyncListDifferDelegationAdapter<ToDestinationUiState>(callback) {
                init {
                    delegatesManager.addDelegate(destinationsSuggestionsDelegate)
                    delegatesManager.addDelegate(destinationsSuggestionsLoadingDelegate)
                }
            }
        }

    }
}