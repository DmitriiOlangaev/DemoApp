package com.demo.demoapp.feature.airTickets.di

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.demoapp.core.common.di.FragmentScope
import com.demo.demoapp.core.model.Ticket
import com.demo.demoapp.core.model.ToDestinationSuggestion
import com.demo.demoapp.feature.airTickets.ui.fragments.ChoosingTicketFragment
import com.demo.demoapp.feature.airTickets.ui.recyclerView.TicketsDelegate
import com.demo.demoapp.feature.airTickets.viewModels.ChoosingTicketFragmentViewModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(dependencies = [AirTicketsDeps::class],
    modules = [ChoosingTicketFragmentModule::class])
@FragmentScope
internal interface ChoosingTicketFragmentComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance choosingTicketFragment: ChoosingTicketFragment,
            @BindsInstance context: Context,
            deps: AirTicketsDeps
        ): ChoosingTicketFragmentComponent
    }

    fun choosingTicketFragmentViewModel(): ChoosingTicketFragmentViewModel.Factory

    fun inject(choosingTicketFragment: ChoosingTicketFragment)
}

@Module
internal interface ChoosingTicketFragmentModule {
    companion object {
        @Provides
        @TicketsRecyclerView
        fun toDestinationsSuggestionsLayoutManager(context: Context): LinearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        @Provides
        @TicketsRecyclerView
        fun ticketsAdapter(ticketsDelegate: TicketsDelegate): AsyncListDifferDelegationAdapter<Ticket> {
            val callback = object : DiffUtil.ItemCallback<Ticket>() {
                override fun areItemsTheSame(
                    oldItem: Ticket,
                    newItem: Ticket
                ): Boolean =
                    oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: Ticket,
                    newItem: Ticket
                ): Boolean =
                    oldItem.id == newItem.id
            }
            return object : AsyncListDifferDelegationAdapter<Ticket>(callback) {
                init {
                    delegatesManager.addDelegate(ticketsDelegate)
                }
            }
        }


        @Provides
        @TicketsRecyclerView
        fun itemDecorations(context: Context): @JvmSuppressWildcards List<RecyclerView.ItemDecoration> =
            listOf(DividerItemDecoration(context, RecyclerView.VERTICAL))
    }
}