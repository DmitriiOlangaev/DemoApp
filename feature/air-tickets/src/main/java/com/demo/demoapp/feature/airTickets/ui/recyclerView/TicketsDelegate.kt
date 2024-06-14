package com.demo.demoapp.feature.airTickets.ui.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.demoapp.core.model.Ticket
import com.demo.demoapp.feature.airTickets.R
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

internal class TicketsDelegate @Inject constructor(private val viewHolderFactory: TicketViewHolder.Factory) :
    AbsListItemAdapterDelegate<Ticket, Ticket, TicketsDelegate.TicketViewHolder>() {
    internal class TicketViewHolder @AssistedInject constructor(
        @Assisted itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        private val badge = itemView.findViewById<TextView>(R.id.badgeTextView)
        private val departureTime = itemView.findViewById<TextView>(R.id.departureTimeTextView)
        private val arrivalTime = itemView.findViewById<TextView>(R.id.arrivalTimeTextView)
        private val travelTime = itemView.findViewById<TextView>(R.id.travelTimeTextView)
        private val optional = itemView.findViewById<TextView>(R.id.optionalTextView)
        private val departureAirport = itemView.findViewById<TextView>(R.id.departureAirportTextView)
        private val arrivalAirport = itemView.findViewById<TextView>(R.id.arrivalAirportTextView)

        @AssistedFactory
        internal interface Factory {
            fun create(itemView: View): TicketViewHolder
        }

        fun bind(ticket: Ticket) {
            if(ticket.badge == null) {
                badge.visibility = View.INVISIBLE
            } else {
                badge.visibility = View.VISIBLE
                badge.text = ticket.badge
            }
            departureTime.text = ticket.departure.date
            arrivalTime.text = ticket.arrival.date
            if(!ticket.hasTransfer) {
                optional.visibility = View.VISIBLE
                optional.text = "Без пересадок"
            } else {
                optional.visibility = View.INVISIBLE
            }
            departureAirport.text = ticket.departure.airport
            arrivalAirport.text = ticket.arrival.airport
        }
    }

    override fun isForViewType(item: Ticket, items: MutableList<Ticket>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): TicketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_ticket, parent, false)
        return viewHolderFactory.create(view)
    }

    override fun onBindViewHolder(item: Ticket, viewHolder: TicketViewHolder, payloads: MutableList<Any>) {
        viewHolder.bind(item)
    }
}