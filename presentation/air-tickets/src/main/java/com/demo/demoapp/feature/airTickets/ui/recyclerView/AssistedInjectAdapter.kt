package com.demo.demoapp.feature.airTickets.ui.recyclerView

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

internal class AssistedInjectAdapter<T> @AssistedInject constructor(
    @Assisted delegates: List<AdapterDelegate<List<T>>>,
    callback: ItemCallback<T>
) : AsyncListDifferDelegationAdapter<T>(callback) {

    @AssistedFactory
    internal interface Factory<T> {
        fun create(delegates: List<AdapterDelegate<List<T>>>): AssistedInjectAdapter<T>
    }

    init {
        delegates.forEach {
            delegatesManager.addDelegate(it)
        }
    }
}