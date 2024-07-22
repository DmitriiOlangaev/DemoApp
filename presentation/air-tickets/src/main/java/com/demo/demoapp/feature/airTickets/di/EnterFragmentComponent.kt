package com.demo.demoapp.feature.airTickets.di

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import com.demo.demoapp.core.common.di.FragmentScope
import com.demo.demoapp.feature.airTickets.ui.fragments.EnterFragment
import com.demo.demoapp.feature.airTickets.ui.recyclerView.ConcertUiState
import com.demo.demoapp.feature.airTickets.ui.recyclerView.ConcertsDelegate
import com.demo.demoapp.feature.airTickets.ui.recyclerView.ConcertsLoadingDelegate
import com.demo.demoapp.feature.airTickets.viewModels.EnterFragmentViewModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope

@Component(
    dependencies = [EnterFragmentDeps::class],
    modules = [EnterFragmentModule::class]
)
@FragmentScope
internal interface EnterFragmentComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance enterFragment: EnterFragment,
            deps: EnterFragmentDeps
        ): EnterFragmentComponent
    }

    fun viewModelFactory(): EnterFragmentViewModel.Factory
    fun inject(enterFragment: EnterFragment)
}

@Module
internal interface EnterFragmentModule {
    companion object {
        @Provides
        fun concertsAdapter(concertsDelegate: ConcertsDelegate, concertsLoadingDelegate: ConcertsLoadingDelegate): AsyncListDifferDelegationAdapter<ConcertUiState> {
            val callback = object : DiffUtil.ItemCallback<ConcertUiState>() {
                override fun areItemsTheSame(
                    oldItem: ConcertUiState,
                    newItem: ConcertUiState
                ): Boolean = oldItem == newItem

                override fun areContentsTheSame(
                    oldItem: ConcertUiState,
                    newItem: ConcertUiState
                ): Boolean = oldItem == newItem
            }
            return object : AsyncListDifferDelegationAdapter<ConcertUiState>(callback) {
                init {
                    delegatesManager.addDelegate(concertsDelegate)
                    delegatesManager.addDelegate(concertsLoadingDelegate)
                }
            }
        }

        @Provides
        fun coroutineScope(enterFragment: EnterFragment): CoroutineScope =
            enterFragment.viewLifecycleOwner.lifecycleScope
    }
}


