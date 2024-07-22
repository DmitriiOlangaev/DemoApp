package com.demo.demoapp.feature.airTickets.ui.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.demoapp.feature.airTickets.databinding.ItemConcertLoadingBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

internal class ConcertsLoadingDelegate @Inject constructor(private val viewHolderFactory: ConcertLoadingViewHolder.Factory) :
    AbsListItemAdapterDelegate<ConcertUiState.Loading, ConcertUiState, ConcertsLoadingDelegate.ConcertLoadingViewHolder>() {
    override fun isForViewType(
        item: ConcertUiState,
        items: MutableList<ConcertUiState>,
        position: Int
    ): Boolean = item is ConcertUiState.Loading

    override fun onCreateViewHolder(parent: ViewGroup): ConcertLoadingViewHolder {
        val binding = ItemConcertLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolderFactory.create(binding)
    }

    override fun onBindViewHolder(
        item: ConcertUiState.Loading,
        viewHolder: ConcertLoadingViewHolder,
        payloads: MutableList<Any>
    ) = viewHolder.bind()


    internal class ConcertLoadingViewHolder @AssistedInject constructor(
        @Assisted binding: ItemConcertLoadingBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        private val sfl = binding.sfl

        fun bind() = Unit

        @AssistedFactory
        internal interface Factory {
            fun create(binding: ItemConcertLoadingBinding): ConcertLoadingViewHolder
        }
    }
}
