package com.demo.demoapp.feature.airTickets.ui.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.demoapp.feature.airTickets.databinding.ItemTownBinding
import com.demo.demoapp.feature.airTickets.viewModels.TownClickedSharedViewModel
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject


internal class TownsDelegate @Inject constructor(private val viewHolderFactory: TownsViewHolder.Factory, private val viewModel : TownClickedSharedViewModel) :
    AbsListItemAdapterDelegate<TownUiState.Success, TownUiState, TownsDelegate.TownsViewHolder>() {

    override fun isForViewType(
        item: TownUiState,
        items: MutableList<TownUiState>,
        position: Int
    ): Boolean = item is TownUiState.Success

    override fun onCreateViewHolder(parent: ViewGroup): TownsViewHolder {
        val binding = ItemTownBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolderFactory.create(binding, viewModel)
    }

    override fun onBindViewHolder(
        item: TownUiState.Success,
        viewHolder: TownsViewHolder,
        payloads: MutableList<Any>
    ): Unit = viewHolder.bind(item.town)

     internal class TownsViewHolder @AssistedInject constructor(@Assisted binding: ItemTownBinding, @Assisted private val viewModel : TownClickedSharedViewModel) :
        RecyclerView.ViewHolder(binding.root) {
        private val tvTown = binding.tvTown

        fun bind(town: String) {
            tvTown.text = town
            itemView.setOnClickListener {
                val result = viewModel.channel.trySend(town)
            }
        }

        @AssistedFactory
        interface Factory {
            fun create(binding: ItemTownBinding, viewModel: TownClickedSharedViewModel): TownsViewHolder
        }
    }
}