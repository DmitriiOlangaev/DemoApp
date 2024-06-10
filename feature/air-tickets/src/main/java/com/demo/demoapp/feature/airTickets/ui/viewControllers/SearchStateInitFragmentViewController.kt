package com.demo.demoapp.feature.airTickets.ui.viewControllers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.demo.demoapp.core.common.di.setCollector
import com.demo.demoapp.core.model.Concert
import com.demo.demoapp.feature.airTickets.databinding.FragmentSearchStateInitBinding
import com.demo.demoapp.feature.airTickets.di.ConcertsRecyclerView
import com.demo.demoapp.feature.airTickets.ui.fragments.SearchStateInitFragment
import com.demo.demoapp.feature.airTickets.viewModels.SearchStateInitFragmentViewModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

internal class SearchStateInitFragmentViewController @AssistedInject constructor(
    @Assisted fragment: SearchStateInitFragment,
    @Assisted private val binding: FragmentSearchStateInitBinding,
    @Assisted private val viewModel: SearchStateInitFragmentViewModel,
    @ConcertsRecyclerView private val layoutManager: LinearLayoutManager,
    @ConcertsRecyclerView private val adapter: AsyncListDifferDelegationAdapter<Concert>,
    @ConcertsRecyclerView private val itemDecorations: @JvmSuppressWildcards List<ItemDecoration>
) : FragmentViewController<SearchStateInitFragment>(fragment) {

    override fun onCreate() {
        setUpConcertsRecyclerView()
        setViewModelCollectors()
    }

    private fun setUpConcertsRecyclerView() {
        binding.concertsRecyclerView.apply {
            layoutManager = this@SearchStateInitFragmentViewController.layoutManager
            adapter = this@SearchStateInitFragmentViewController.adapter
            itemDecorations.forEach {
                addItemDecoration(it)
            }
        }

    }


    private fun setViewModelCollectors() {
        fragment.setCollector {
            viewModel.concertsFlow.collect {
                adapter.items = it
            }
        }
    }

    @AssistedFactory
    internal interface Factory {
        fun create(
            searchStateInitFragment: SearchStateInitFragment,
            binding: FragmentSearchStateInitBinding,
            viewModel: SearchStateInitFragmentViewModel
        ): SearchStateInitFragmentViewController
    }
}