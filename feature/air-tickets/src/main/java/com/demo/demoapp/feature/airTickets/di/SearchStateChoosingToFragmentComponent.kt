package com.demo.demoapp.feature.airTickets.di

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.demoapp.core.model.ToDestinationSuggestion
import com.demo.demoapp.feature.airTickets.ui.fragments.SearchStateChoosingToFragment
import com.demo.demoapp.feature.airTickets.ui.viewControllers.SearchStateChoosingToFragmentViewController
import com.demo.demoapp.feature.airTickets.viewModels.SearchStateChoosingToFragmentViewModel
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [SearchStateChoosingToFragmentModule::class])
@SearchStateFragmentScope
internal interface SearchStateChoosingToFragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance searchStateChoosingToFragment: SearchStateChoosingToFragment): SearchStateChoosingToFragmentComponent
    }

    fun searchStateChoosingToFragmentViewController(): SearchStateChoosingToFragmentViewController.Factory

    fun searchStateChoosingToFragmentViewModel(): SearchStateChoosingToFragmentViewModel.Factory

    fun inject(searchStateChoosingToFragment: SearchStateChoosingToFragment)
}

@Module
internal interface SearchStateChoosingToFragmentModule {
    companion object {

        @Provides
        @ToDestinationsSuggestionsRecyclerView
        fun toDestinationsSuggestionsLayoutManager(context: Context): LinearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        @Provides
        fun callback(): DiffUtil.ItemCallback<ToDestinationSuggestion> =
            object : DiffUtil.ItemCallback<ToDestinationSuggestion>() {
                override fun areItemsTheSame(
                    oldItem: ToDestinationSuggestion,
                    newItem: ToDestinationSuggestion
                ): Boolean =
                    oldItem.town == newItem.town

                override fun areContentsTheSame(
                    oldItem: ToDestinationSuggestion,
                    newItem: ToDestinationSuggestion
                ): Boolean =
                    oldItem.town == newItem.town
            }


        @Provides
        @ToDestinationsSuggestionsRecyclerView
        fun itemDecorations(context: Context): @JvmSuppressWildcards List<RecyclerView.ItemDecoration> =
            listOf(DividerItemDecoration(context, RecyclerView.VERTICAL))

    }
}