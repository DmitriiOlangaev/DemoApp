package com.demo.demoapp.feature.airTickets.ui.viewControllers

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.demoapp.core.common.di.setCollector
import com.demo.demoapp.core.model.TicketOffer
import com.demo.demoapp.feature.airTickets.databinding.FragmentSearchStateDestinationsChosenBinding
import com.demo.demoapp.feature.airTickets.di.TicketSOffersRecyclerView
import com.demo.demoapp.feature.airTickets.ui.fragments.ChoosingRouteFragmentDirections
import com.demo.demoapp.feature.airTickets.ui.fragments.SearchStateDestinationsChosenFragment
import com.demo.demoapp.feature.airTickets.ui.fragments.SearchStateDestinationsChosenFragmentArgs
import com.demo.demoapp.feature.airTickets.ui.fragments.TicketData
import com.demo.demoapp.feature.airTickets.viewModels.SearchStateDestinationsChosenFragmentViewModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import java.util.Date

internal class SearchStateDestinationsChosenFragmentViewController @AssistedInject constructor(
    @Assisted fragment: SearchStateDestinationsChosenFragment,
    @Assisted private val navArgs: SearchStateDestinationsChosenFragmentArgs,
    @Assisted private val binding: FragmentSearchStateDestinationsChosenBinding,
    @Assisted private val viewModel: SearchStateDestinationsChosenFragmentViewModel,
    @TicketSOffersRecyclerView private val adapter: AsyncListDifferDelegationAdapter<TicketOffer>,
    @TicketSOffersRecyclerView private val layoutManager: LinearLayoutManager,
    @TicketSOffersRecyclerView private val itemDecorations: @JvmSuppressWildcards List<RecyclerView.ItemDecoration>
) : FragmentViewController<SearchStateDestinationsChosenFragment>(fragment) {
    @AssistedFactory
    interface Factory {
        fun create(
            fragment: SearchStateDestinationsChosenFragment,
            navArgs: SearchStateDestinationsChosenFragmentArgs,
            binding: FragmentSearchStateDestinationsChosenBinding,
            viewModel: SearchStateDestinationsChosenFragmentViewModel
        ): SearchStateDestinationsChosenFragmentViewController
    }

    override fun onCreate() {
        super.onCreate()
        setUpDatePickerButton()
        setUpRecyclerView()
        setUpTicketOffersCollector()
        setUpOpenAllTicketsButton()
    }

    private fun setUpTicketOffersCollector() {
        fragment.setCollector {
            viewModel.ticketsOffersFlow.collect {
                adapter.items = it
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.ticketsOffersRecyclerView.apply {
            layoutManager = this@SearchStateDestinationsChosenFragmentViewController.layoutManager
            adapter = this@SearchStateDestinationsChosenFragmentViewController.adapter
            itemDecorations.forEach {
                addItemDecoration(it)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUpDatePickerButton() {
        binding.chooseDateButton.text = Date(System.currentTimeMillis()).toString()
        binding.chooseDateButton.setOnClickListener {
            val datePickerDialog = DatePickerDialog(fragment.requireContext())
            datePickerDialog.setOnDateSetListener { _, year, month, day ->
                binding.chooseDateButton.text = "$year-${month + 1}-$day"
            }
            datePickerDialog.show()
        }
    }

    private fun setUpOpenAllTicketsButton() {
        binding.openAllTicketsButton.setOnClickListener {
            fragment.requireParentFragment().requireParentFragment().findNavController().navigate(
                ChoosingRouteFragmentDirections.actionChoosingRouteFragmentToChoosingTicketFragment(
                    TicketData(navArgs.from, navArgs.to)
                )
            )
        }
    }
}