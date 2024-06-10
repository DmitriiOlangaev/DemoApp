package com.demo.demoapp.feature.airTickets.ui.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.demoapp.core.model.Concert
import com.demo.demoapp.feature.airTickets.R
import com.demo.demoapp.feature.airTickets.ui.AsyncImageView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import javax.inject.Inject

internal class ConcertsDelegate @Inject constructor(private val viewHolderFactory: ConcertViewHolder.Factory) :
    AbsListItemAdapterDelegate<Concert, Concert, ConcertsDelegate.ConcertViewHolder>() {


    override fun isForViewType(item: Concert, items: MutableList<Concert>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): ConcertViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_concert, parent, false)
        return viewHolderFactory.create(view)
    }

    override fun onBindViewHolder(
        item: Concert,
        viewHolder: ConcertViewHolder,
        payloads: MutableList<Any>
    ): Unit = viewHolder.bind(item)


    internal class ConcertViewHolder @AssistedInject constructor(
        @Assisted itemView: View,
        asyncImageViewFactory: AsyncImageView.Factory
    ) : RecyclerView.ViewHolder(itemView) {
        @AssistedFactory
        internal interface Factory {
            fun create(itemView: View): ConcertViewHolder
        }

        private val imageView = itemView.findViewById<ImageView>(R.id.image)
        private val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)
        private val groupNameTextView = itemView.findViewById<TextView>(R.id.groupNameTextView)
        private val cityNameTextView = itemView.findViewById<TextView>(R.id.cityNameTextView)
        private val priceTextView = itemView.findViewById<TextView>(R.id.priceTextView)
        private val asyncImageView = asyncImageViewFactory.create(imageView, progressBar)
        fun bind(concert: Concert) {
            groupNameTextView.text = concert.title
            cityNameTextView.text = concert.town
            priceTextView.text = concert.price.value.toString()
            asyncImageView.downloadImage(concert.id)

        }
    }
}