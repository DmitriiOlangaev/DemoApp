package com.demo.demoapp.feature.airTickets.ui.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.demoapp.feature.airTickets.databinding.ItemTownLoadingBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

internal class TownsLoadingDelegate @Inject constructor(private val viewHolderFactory: TownsLoadingViewHolder.Factory) :
    AbsListItemAdapterDelegate<TownUiState.Loading, TownUiState, TownsLoadingDelegate.TownsLoadingViewHolder>() {

    override fun isForViewType(
        item: TownUiState,
        items: MutableList<TownUiState>,
        position: Int
    ): Boolean = item is TownUiState.Loading

    override fun onCreateViewHolder(parent: ViewGroup): TownsLoadingViewHolder {
        val binding = ItemTownLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolderFactory.create(binding)
    }

    override fun onBindViewHolder(
        item: TownUiState.Loading,
        viewHolder: TownsLoadingViewHolder,
        payloads: MutableList<Any>
    ) = Unit

    internal class TownsLoadingViewHolder @AssistedInject constructor(@Assisted binding: ItemTownLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {


        @AssistedFactory
        interface Factory {
            fun create(binding: ItemTownLoadingBinding): TownsLoadingViewHolder
        }

    }
}
