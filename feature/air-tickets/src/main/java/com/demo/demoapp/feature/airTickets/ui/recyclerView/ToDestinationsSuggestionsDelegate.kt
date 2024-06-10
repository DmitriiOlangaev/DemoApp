package com.demo.demoapp.feature.airTickets.ui.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.demoapp.core.model.ToDestinationSuggestion
import com.demo.demoapp.feature.airTickets.R
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

internal class ToDestinationsSuggestionsDelegate @AssistedInject constructor(
    @Assisted private val onClick: (ToDestinationSuggestion) -> kotlin.Unit,
    private val viewHolderFactory: ToDestinationSuggestionViewHolder.Factory
) :
    AbsListItemAdapterDelegate<ToDestinationSuggestion, ToDestinationSuggestion, ToDestinationsSuggestionsDelegate.ToDestinationSuggestionViewHolder>() {

    @AssistedFactory
    internal interface Factory {
        fun create(onClick: (ToDestinationSuggestion) -> Unit): ToDestinationsSuggestionsDelegate

    }

    override fun isForViewType(
        item: ToDestinationSuggestion,
        items: MutableList<ToDestinationSuggestion>,
        position: Int
    ): Boolean = true

    override fun onCreateViewHolder(parent: ViewGroup): ToDestinationSuggestionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_to_destination_suggestion, parent, false)
        return viewHolderFactory.create(view, onClick)
    }

    override fun onBindViewHolder(
        item: ToDestinationSuggestion,
        viewHolder: ToDestinationSuggestionViewHolder,
        payloads: MutableList<Any>
    ): Unit = viewHolder.bind(item)

    internal class ToDestinationSuggestionViewHolder @AssistedInject constructor(
        @Assisted itemView: View,
        @Assisted private val onClick: (ToDestinationSuggestion) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val townTextView = itemView.findViewById<TextView>(R.id.townTextView)
        private val hintTextView = itemView.findViewById<TextView>(R.id.hintTextView)

        @AssistedFactory
        internal interface Factory {
            fun create(
                itemView: View,
                onClick: (ToDestinationSuggestion) -> Unit
            ): ToDestinationSuggestionViewHolder
        }

        fun bind(toDestinationSuggestion: ToDestinationSuggestion) {
            itemView.setOnClickListener { onClick(toDestinationSuggestion) }
            townTextView.text = toDestinationSuggestion.town
            hintTextView.text = toDestinationSuggestion.hint
        }
    }
}