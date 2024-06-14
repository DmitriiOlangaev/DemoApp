package com.demo.demoapp.feature.airTickets.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.demoapp.core.common.di.findDependencies
import com.demo.demoapp.core.common.di.lazyViewModel
import com.demo.demoapp.core.common.di.setCollector
import com.demo.demoapp.core.model.Ticket
import com.demo.demoapp.feature.airTickets.databinding.FragmentChoosingTicketBinding
import com.demo.demoapp.feature.airTickets.di.ChoosingTicketFragmentComponent
import com.demo.demoapp.feature.airTickets.di.DaggerChoosingTicketFragmentComponent
import com.demo.demoapp.feature.airTickets.di.TicketsRecyclerView
import com.demo.demoapp.feature.airTickets.viewModels.ChoosingTicketFragmentViewModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

internal class ChoosingTicketFragment : Fragment() {
    private var _binding: FragmentChoosingTicketBinding? = null
    internal val binding: FragmentChoosingTicketBinding
        get() = _binding!!

    private val navArgs : ChoosingTicketFragmentArgs by navArgs()

    private val viewModel by lazyViewModel<ChoosingTicketFragmentViewModel> { stateHandle -> component.choosingTicketFragmentViewModel().create(stateHandle, navArgs.ticketData)  }

    private val component : ChoosingTicketFragmentComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): ChoosingTicketFragmentComponent =
        DaggerChoosingTicketFragmentComponent.factory()
            .create(this, requireActivity(), findDependencies())

    @Inject
    @TicketsRecyclerView
    lateinit var layoutManager : LinearLayoutManager

    @Inject
    @TicketsRecyclerView
    lateinit var adapter : AsyncListDifferDelegationAdapter<Ticket>

    @Inject
    @TicketsRecyclerView
    @JvmSuppressWildcards
    lateinit var itemDecorations : List<RecyclerView.ItemDecoration>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChoosingTicketBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)
        binding.routeTextView.text = "${navArgs.ticketData.from} - ${navArgs.ticketData.to}"
        binding.passengersTextView.text = "23 февраля, 1 пассажир"
        binding.ticketsRecyclerView.apply {
            layoutManager = this@ChoosingTicketFragment.layoutManager
            adapter = this@ChoosingTicketFragment.adapter
            itemDecorations.forEach {
                addItemDecoration(it)
            }
        }
        setCollector {
            viewModel.ticketsFlow.collect {
                adapter.items = it
            }
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}