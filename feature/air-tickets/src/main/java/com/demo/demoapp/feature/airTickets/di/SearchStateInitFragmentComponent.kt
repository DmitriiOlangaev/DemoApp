package com.demo.demoapp.feature.airTickets.di

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.demo.demoapp.core.model.Concert
import com.demo.demoapp.feature.airTickets.ui.fragments.SearchStateInitFragment
import com.demo.demoapp.feature.airTickets.ui.recyclerView.ConcertsDelegate
import com.demo.demoapp.feature.airTickets.ui.viewControllers.SearchStateInitFragmentViewController
import com.demo.demoapp.feature.airTickets.viewModels.SearchStateInitFragmentViewModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [SearchStateInitFragmentModule::class])
@SearchStateFragmentScope
internal interface SearchStateInitFragmentComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance searchStateInitFragment: SearchStateInitFragment): SearchStateInitFragmentComponent
    }

    fun searchStateInitFragmentViewModel(): SearchStateInitFragmentViewModel.Factory

    fun searchStateInitFragmentViewController(): SearchStateInitFragmentViewController.Factory

    fun inject(searchStateInitFragment: SearchStateInitFragment)
}

@Module
internal interface SearchStateInitFragmentModule {

    companion object {

        @Provides
        @ConcertsRecyclerView
        fun concertsLayoutManager(context: Context): LinearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


        @Provides
        @ConcertsRecyclerView
        fun concertsAdapter(concertsDelegate: ConcertsDelegate): AsyncListDifferDelegationAdapter<Concert> {
            val callback = object : DiffUtil.ItemCallback<Concert>() {
                override fun areItemsTheSame(oldItem: Concert, newItem: Concert): Boolean =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(oldItem: Concert, newItem: Concert): Boolean =
                    oldItem.id == newItem.id
            }
            return object : AsyncListDifferDelegationAdapter<Concert>(callback) {
                init {
                    delegatesManager.addDelegate(concertsDelegate)
                }
            }
        }

        @Provides
        @ConcertsRecyclerView
        fun itemDecorations(context: Context): @JvmSuppressWildcards List<ItemDecoration> =
            listOf(DividerItemDecoration(context, RecyclerView.HORIZONTAL))

    }
}

