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
import com.demo.demoapp.core.common.setVisibleOrGone
import com.demo.demoapp.feature.airTickets.databinding.FragmentChoosingTownBinding
import com.demo.demoapp.feature.airTickets.di.ChoosingTownFragmentComponent
import com.demo.demoapp.feature.airTickets.di.DaggerChoosingTownFragmentComponent
import com.demo.demoapp.feature.airTickets.ui.recyclerView.TownUiState
import com.demo.demoapp.feature.airTickets.viewModels.ChoosingTownFragmentViewModel
import com.demo.demoapp.feature.airTickets.viewModels.EnteredTownSharedViewModel
import com.demo.demoapp.feature.airTickets.viewModels.TownClickedSharedViewModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import kotlinx.coroutines.CompletableDeferred
import java.util.Collections
import javax.inject.Inject

internal class ChoosingTownFragment : Fragment() {
    private var _binding: FragmentChoosingTownBinding? = null
    private val binding: FragmentChoosingTownBinding
        get() = _binding!!
    private val component: ChoosingTownFragmentComponent by lazy {
        initializeComponent()
    }
    private val viewModel by lazyViewModel<ChoosingTownFragmentViewModel> { stateHandle ->
        component.viewModelFactory().create(stateHandle)
    }
    private val enteredTownSharedViewModel: EnteredTownSharedViewModel by viewModels({ requireParentFragment().requireParentFragment() })

    private val townClickedSharedViewModel: TownClickedSharedViewModel by viewModels({ requireParentFragment().requireParentFragment() })

    private lateinit var towns : List<String>

    private val townsLoadedDeferred = CompletableDeferred<Unit>()


    @Inject
    lateinit var adapter: AsyncListDifferDelegationAdapter<TownUiState>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChoosingTownBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTownsCollector()
        setEnteredTownCollector()
        setUpRV()
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initializeComponent(): ChoosingTownFragmentComponent =
        DaggerChoosingTownFragmentComponent.factory()
            .create(this, enteredTownSharedViewModel, townClickedSharedViewModel, findDependencies())

    private fun setUpRV() {
        binding.rvTowns.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter = this@ChoosingTownFragment.adapter
            addItemDecoration(
                DividerItemDecoration(
                    requireActivity(),
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    private fun setTownsCollector() {
        setCollector {
            viewModel.townsStateFlow.collect {
                when (it) {
                    is Result.Success -> {
                        towns = it.data
                        townsLoadedDeferred.complete(Unit)
                    }
                    is Result.Loading -> adapter.items =
                        Collections.nCopies(20, TownUiState.Loading).toList()

                    is Result.Error -> {
                        Log.e("ErrorTowns", it.exception.stackTraceToString())
                        towns = emptyList()
                        Toast.makeText(
                            requireActivity(),
                            "Проблемы с соединением",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun setEnteredTownCollector() {
        setCollector {
            enteredTownSharedViewModel.enteredTown.collect { enteredTown ->
                townsLoadedDeferred.await()
                 adapter.items = towns.filter {
                    it.startsWith(enteredTown)
                }.also{
                     binding.rvTowns.setVisibleOrGone(it.isNotEmpty())
                 }.map {
                    TownUiState.Success(it)
                }
            }
        }
    }
}