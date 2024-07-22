package com.demo.demoapp.feature.airTickets.ui.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.demoapp.domain.model.DestinationSuggestion
import com.demo.demoapp.feature.airTickets.databinding.ItemDestinationSuggestionBinding
import com.demo.demoapp.feature.airTickets.viewModels.TownClickedSharedViewModel
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

internal class DestinationsSuggestionsDelegate @Inject constructor(
    private val viewModel: TownClickedSharedViewModel,
    private val viewHolderFactory: DestinationSuggestionViewHolder.Factory
) :
    AbsListItemAdapterDelegate<ToDestinationUiState.Success, ToDestinationUiState, DestinationsSuggestionsDelegate.DestinationSuggestionViewHolder>() {

    override fun isForViewType(
        item: ToDestinationUiState,
        items: MutableList<ToDestinationUiState>,
        position: Int
    ): Boolean = item is ToDestinationUiState.Success

    override fun onCreateViewHolder(parent: ViewGroup): DestinationSuggestionViewHolder {
        val binding = ItemDestinationSuggestionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return viewHolderFactory.create(binding, viewModel)
    }

    override fun onBindViewHolder(
        item: ToDestinationUiState.Success,
        viewHolder: DestinationSuggestionViewHolder,
        payloads: MutableList<Any>
    ): Unit = viewHolder.bind(item.destinationSuggestion)

    internal class DestinationSuggestionViewHolder @AssistedInject constructor(
        @Assisted binding: ItemDestinationSuggestionBinding,
        @Assisted private val viewModel: TownClickedSharedViewModel,
    ) : RecyclerView.ViewHolder(binding.root) {
        private val tvTown = binding.tvTown
        private val tvHint = binding.tvHint

        @AssistedFactory
        internal interface Factory {
            fun create(
                binding: ItemDestinationSuggestionBinding,
                viewModel: TownClickedSharedViewModel
            ): DestinationSuggestionViewHolder
        }

        fun bind(destinationSuggestion: DestinationSuggestion) {
            itemView.setOnClickListener { viewModel.channel.trySend(destinationSuggestion.town) }
            tvTown.text = destinationSuggestion.town
            tvHint.text = destinationSuggestion.hint
        }
    }
}