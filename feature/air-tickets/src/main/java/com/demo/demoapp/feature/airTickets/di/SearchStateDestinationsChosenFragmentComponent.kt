package com.demo.demoapp.feature.airTickets.di

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.demoapp.core.model.TicketOffer
import com.demo.demoapp.feature.airTickets.ui.fragments.SearchStateDestinationsChosenFragment
import com.demo.demoapp.feature.airTickets.ui.recyclerView.TicketsOffersDelegate
import com.demo.demoapp.feature.airTickets.ui.viewControllers.SearchStateDestinationsChosenFragmentViewController
import com.demo.demoapp.feature.airTickets.viewModels.SearchStateDestinationsChosenFragmentViewModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [SearchStateDestinationsChosenFragmentModule::class])
@SearchStateFragmentScope
internal interface SearchStateDestinationsChosenFragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance searchStateDestinationsChosenFragment: SearchStateDestinationsChosenFragment
        ): SearchStateDestinationsChosenFragmentComponent
    }

    fun searchStateDestinationsChosenFragmentViewController(): SearchStateDestinationsChosenFragmentViewController.Factory

    fun searchStateDestinationsChosenFragmentViewModel(): SearchStateDestinationsChosenFragmentViewModel.Factory

    fun inject(searchStateDestinationsChosenFragment: SearchStateDestinationsChosenFragment)
}

@Module
internal interface SearchStateDestinationsChosenFragmentModule {

    companion object {

        @Provides
        @TicketSOffersRecyclerView
        fun concertsLayoutManager(context: Context): LinearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


        @Provides
        @TicketSOffersRecyclerView
        fun ticketsOffersAdapter(ticketsOffersDelegate: TicketsOffersDelegate): AsyncListDifferDelegationAdapter<TicketOffer> {
            val callback = object : DiffUtil.ItemCallback<TicketOffer>() {
                override fun areItemsTheSame(oldItem: TicketOffer, newItem: TicketOffer): Boolean =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: TicketOffer,
                    newItem: TicketOffer
                ): Boolean =
                    oldItem.id == newItem.id
            }
            return object : AsyncListDifferDelegationAdapter<TicketOffer>(callback) {
                init {
                    delegatesManager.addDelegate(ticketsOffersDelegate)
                }
            }
        }

        @Provides
        @TicketSOffersRecyclerView
        fun itemDecorations(context: Context): @JvmSuppressWildcards List<RecyclerView.ItemDecoration> =
            listOf(DividerItemDecoration(context, RecyclerView.HORIZONTAL))

    }
}
