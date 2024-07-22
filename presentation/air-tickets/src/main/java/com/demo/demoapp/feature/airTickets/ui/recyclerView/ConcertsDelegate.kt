package com.demo.demoapp.feature.airTickets.ui.recyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.demoapp.domain.model.Concert
import com.demo.demoapp.feature.airTickets.databinding.ItemConcertBinding
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import java.text.NumberFormat
import javax.inject.Inject

internal class ConcertsDelegate @Inject constructor(private val viewHolderFactory: ConcertViewHolder.Factory) :
    AbsListItemAdapterDelegate<ConcertUiState.Success, ConcertUiState, ConcertsDelegate.ConcertViewHolder>() {


    override fun isForViewType(
        item: ConcertUiState,
        items: MutableList<ConcertUiState>,
        position: Int
    ): Boolean = item is ConcertUiState.Success

    override fun onCreateViewHolder(parent: ViewGroup): ConcertViewHolder {
        val binding = ItemConcertBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return viewHolderFactory.create(binding)
    }

    override fun onBindViewHolder(
        item: ConcertUiState.Success,
        viewHolder: ConcertViewHolder,
        payloads: MutableList<Any>
    ): Unit = viewHolder.bind(item.concert)


    internal class ConcertViewHolder @AssistedInject constructor(
        @Assisted binding: ItemConcertBinding) : RecyclerView.ViewHolder(binding.root) {
        @AssistedFactory
        internal interface Factory {
            fun create(binding: ItemConcertBinding): ConcertViewHolder
        }

        private val group = binding.tvGroupName
        private val town = binding.tvTown
        private val price = binding.tvPrice
        private val image = binding.ivImage

        @SuppressLint("SetTextI18n")
        fun bind(concert: Concert) {
            group.text = concert.title
            town.text = concert.town
            price.text = "от ${NumberFormat.getNumberInstance().format(concert.price.value)}₽"
            image.setImageDrawable(concert.drawable)
        }
    }
}