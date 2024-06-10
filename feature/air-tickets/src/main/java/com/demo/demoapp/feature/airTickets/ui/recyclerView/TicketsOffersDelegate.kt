package com.demo.demoapp.feature.airTickets.ui.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.demoapp.core.model.TicketOffer
import com.demo.demoapp.feature.airTickets.R
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

internal class TicketsOffersDelegate @Inject constructor(private val viewHolderFactory: TicketOfferViewHolder.Factory) :
    AbsListItemAdapterDelegate<TicketOffer, TicketOffer, TicketsOffersDelegate.TicketOfferViewHolder>() {


    override fun isForViewType(
        item: TicketOffer,
        items: MutableList<TicketOffer>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): TicketOfferViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_ticket_offer, parent, false)
        return viewHolderFactory.create(view)
    }

    override fun onBindViewHolder(
        item: TicketOffer,
        viewHolder: TicketOfferViewHolder,
        payloads: MutableList<Any>
    ): Unit = viewHolder.bind(item)


    internal class TicketOfferViewHolder @AssistedInject constructor(
        @Assisted itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val companyNameTextView = itemView.findViewById<TextView>(R.id.companyNameTextView)
        private val priceTextView = itemView.findViewById<TextView>(R.id.priceTextView)
        private val timesTextView = itemView.findViewById<TextView>(R.id.timesTextView)

        @AssistedFactory
        internal interface Factory {
            fun create(itemView: View): TicketOfferViewHolder
        }

        fun bind(ticketOffer: TicketOffer) {
            companyNameTextView.text = ticketOffer.title
            priceTextView.text = ticketOffer.price.toString()
            timesTextView.text = ticketOffer.timeRange.toString()
        }
    }
}
