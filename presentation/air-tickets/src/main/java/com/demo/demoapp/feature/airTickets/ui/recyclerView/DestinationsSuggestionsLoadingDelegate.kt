package com.demo.demoapp.feature.airTickets.ui.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.demoapp.feature.airTickets.databinding.ItemDestinationSuggestionLoadingBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

internal class DestinationsSuggestionsLoadingDelegate @Inject constructor(private val viewHolderFactory: DestinationSuggestionLoadingViewHolder.Factory) :
    AbsListItemAdapterDelegate<ToDestinationUiState.Loading, ToDestinationUiState, DestinationsSuggestionsLoadingDelegate.DestinationSuggestionLoadingViewHolder>() {

    override fun isForViewType(
        item: ToDestinationUiState,
        items: MutableList<ToDestinationUiState>,
        position: Int
    ): Boolean = item is ToDestinationUiState.Loading

    override fun onCreateViewHolder(parent: ViewGroup): DestinationSuggestionLoadingViewHolder {
        val binding = ItemDestinationSuggestionLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolderFactory.create(binding)
    }

    override fun onBindViewHolder(
        item: ToDestinationUiState.Loading,
        viewHolder: DestinationSuggestionLoadingViewHolder,
        payloads: MutableList<Any>
    ) = viewHolder.bind()
    internal class DestinationSuggestionLoadingViewHolder @AssistedInject constructor(
        @Assisted binding: ItemDestinationSuggestionLoadingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind() = Unit

        @AssistedFactory
        interface Factory {
            fun create(binding: ItemDestinationSuggestionLoadingBinding): DestinationSuggestionLoadingViewHolder
        }
    }
}