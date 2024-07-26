package com.demo.demoapp.feature.airTickets.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.demoapp.core.common.Result
import com.demo.demoapp.core.common.findDependencies
import com.demo.demoapp.core.common.lazyViewModel
import com.demo.demoapp.core.common.setCollector
import com.demo.demoapp.domain.model.DestinationSuggestion
import com.demo.demoapp.feature.airTickets.databinding.FragmentChoosingToBinding
import com.demo.demoapp.feature.airTickets.di.ChoosingToFragmentComponent
import com.demo.demoapp.feature.airTickets.di.DaggerChoosingToFragmentComponent
import com.demo.demoapp.feature.airTickets.ui.recyclerView.ToDestinationUiState
import com.demo.demoapp.feature.airTickets.viewModels.ChoosingToFragmentViewModel
import com.demo.demoapp.feature.airTickets.viewModels.TownClickedSharedViewModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import java.util.Collections
import javax.inject.Inject

internal class ChoosingToFragment : Fragment() {
    private var _binding: FragmentChoosingToBinding? = null
    private val binding: FragmentChoosingToBinding
        get() = _binding!!
    private val component: ChoosingToFragmentComponent by lazy {
        initializeComponent()
    }

    private val viewModel: ChoosingToFragmentViewModel by lazyViewModel { stateHandle ->
        component.viewModelFactory().create(stateHandle)
    }

    private val townClickedViewModel: TownClickedSharedViewModel by viewModels({ requireParentFragment().requireParentFragment() })

    @Inject
    lateinit var adapter : AsyncListDifferDelegationAdapter<ToDestinationUiState>


    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChoosingToBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToDestinationsCollector()
        setUpRV()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initializeComponent(): ChoosingToFragmentComponent =
        DaggerChoosingToFragmentComponent.factory()
            .create(this, townClickedViewModel, findDependencies())

    private fun setUpRV() {
        binding.rvToDestsSuggestions.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter = this@ChoosingToFragment.adapter
            addItemDecoration(
                DividerItemDecoration(
                    requireActivity(),
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }
    private fun setToDestinationsCollector() {
        setCollector {
            viewModel.toDestinationsStateFlow.collect {
                when(it) {
                    is Result.Success -> {
                        adapter.items = it.data.map<DestinationSuggestion, ToDestinationUiState> {
                            destinationSuggestion -> ToDestinationUiState.Success(destinationSuggestion)
                        }
                    }
                    is Result.Loading -> {
                        adapter.items = Collections.nCopies<ToDestinationUiState>(5, ToDestinationUiState.Loading).toList()
                    }
                    is Result.Error -> {
                        Log.e("ErrorToDestinationsSuggestions", it.exception.stackTraceToString())
                        adapter.items = emptyList()
                        Toast.makeText(requireActivity(), "Проблемы с соединением", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

}