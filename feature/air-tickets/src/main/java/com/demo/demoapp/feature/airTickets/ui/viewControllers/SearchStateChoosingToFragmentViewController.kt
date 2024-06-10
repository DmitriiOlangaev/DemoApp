package com.demo.demoapp.feature.airTickets.ui.viewControllers

import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.demo.demoapp.core.common.di.setCollector
import com.demo.demoapp.core.model.ToDestinationSuggestion
import com.demo.demoapp.feature.airTickets.databinding.FragmentSearchStateChoosingToBinding
import com.demo.demoapp.feature.airTickets.di.ToDestinationsSuggestionsRecyclerView
import com.demo.demoapp.feature.airTickets.ui.fragments.ChoosingRouteFragmentDirections
import com.demo.demoapp.feature.airTickets.ui.fragments.SearchStateChoosingToFragment
import com.demo.demoapp.feature.airTickets.ui.recyclerView.AssistedInjectAdapter
import com.demo.demoapp.feature.airTickets.ui.recyclerView.ToDestinationsSuggestionsDelegate
import com.demo.demoapp.feature.airTickets.viewModels.SearchStateChoosingToFragmentViewModel
import com.demo.demoapp.feature.airTickets.viewModels.ToDestinationSharedViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

internal class SearchStateChoosingToFragmentViewController @AssistedInject constructor(
    @Assisted fragment: SearchStateChoosingToFragment,
    @Assisted private val binding: FragmentSearchStateChoosingToBinding,
    @Assisted private val viewModel: SearchStateChoosingToFragmentViewModel,
    @Assisted private val sharedViewModel: ToDestinationSharedViewModel,
    @ToDestinationsSuggestionsRecyclerView private val layoutManager: LinearLayoutManager,
    @ToDestinationsSuggestionsRecyclerView private val itemDecorations: @JvmSuppressWildcards List<ItemDecoration>,
    private val adapterFactory: AssistedInjectAdapter.Factory<ToDestinationSuggestion>,
    private val toDestinationsSuggestionsDelegate: ToDestinationsSuggestionsDelegate.Factory
) :
    FragmentViewController<SearchStateChoosingToFragment>(fragment) {
    private val adapter = adapterFactory.create(listOf(toDestinationsSuggestionsDelegate.create {
        fragment.viewLifecycleOwner.lifecycleScope.launch {
            sharedViewModel.toDestinationChannel.send(it.town)
        }
    }))


    override fun onCreate() {
        super.onCreate()
        setUpToDestinationsSuggestionsRecyclerView()
        setUpButtons()
    }

    override fun onStart() {
        super.onStart()
        setUpViewModelCollectors()
    }

    private fun setUpChangeToButton(button: ImageButton, textView: TextView) {
        button.setOnClickListener {
            fragment.viewLifecycleOwner.lifecycleScope.launch {
                sharedViewModel.toDestinationChannel.send(textView.text.toString())
            }
        }
    }

    private fun setUpMockButton(button: ImageButton) {
        button.setOnClickListener {
            val action = ChoosingRouteFragmentDirections.actionChoosingRouteFragmentToMockFragment()
            fragment.requireParentFragment().requireParentFragment().findNavController()
                .navigate(action)
        }
    }

    private fun setUpButtons() {
        setUpChangeToButton(binding.whereverButton, binding.whereverTitleTextView)
        setUpMockButton(binding.hardRouteButton)
        setUpMockButton(binding.holidaysButton)
        setUpMockButton(binding.hotTicketsButton)
    }

    private fun setUpViewModelCollectors() {
        fragment.setCollector {
            viewModel.toDestinationSuggestionsFlow.collect {
                adapter.items = it
            }
        }
    }

    private fun setUpToDestinationsSuggestionsRecyclerView() {
        binding.toDestinationSuggestionsRecyclerView.apply {
            layoutManager = this@SearchStateChoosingToFragmentViewController.layoutManager
            adapter = this@SearchStateChoosingToFragmentViewController.adapter
            itemDecorations.forEach {
                addItemDecoration(it)
            }
        }
    }


    @AssistedFactory
    internal interface Factory {
        fun create(
            searchStateChoosingToFragment: SearchStateChoosingToFragment,
            binding: FragmentSearchStateChoosingToBinding,
            viewModel: SearchStateChoosingToFragmentViewModel,
            sharedViewModel: ToDestinationSharedViewModel
        ): SearchStateChoosingToFragmentViewController
    }

}