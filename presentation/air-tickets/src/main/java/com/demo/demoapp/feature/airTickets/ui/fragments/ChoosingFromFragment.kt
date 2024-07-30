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
import com.demo.demoapp.feature.airTickets.databinding.FragmentChoosingFromBinding
import com.demo.demoapp.feature.airTickets.di.ChoosingFromFragmentComponent
import com.demo.demoapp.feature.airTickets.di.DaggerChoosingFromFragmentComponent
import com.demo.demoapp.feature.airTickets.ui.recyclerView.TownUiState
import com.demo.demoapp.feature.airTickets.viewModels.ChoosingFromFragmentViewModel
import com.demo.demoapp.feature.airTickets.viewModels.TownClickedSharedViewModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import java.util.Collections
import javax.inject.Inject

internal class ChoosingFromFragment : Fragment(){
    private var _binding : FragmentChoosingFromBinding? = null
    private val binding : FragmentChoosingFromBinding
        get() = _binding!!
    private val component : ChoosingFromFragmentComponent by lazy {
        initializeComponent()
    }

    private val viewModel : ChoosingFromFragmentViewModel by lazyViewModel { stateHandle -> component.viewModelFactory().create(stateHandle)}

    private val townClickedSharedViewModel : TownClickedSharedViewModel by viewModels({requireParentFragment().requireParentFragment()})

    @Inject
    lateinit var adapter : AsyncListDifferDelegationAdapter<TownUiState>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChoosingFromBinding.inflate(inflater, container, false)
       return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setPrevEnteredFromCollector()
        setUpRV()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setUpRV() {
        binding.rvPrevEntered.apply {
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            adapter = this@ChoosingFromFragment.adapter
            addItemDecoration(
                DividerItemDecoration(
                    requireActivity(),
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    private fun setPrevEnteredFromCollector() {
        setCollector {
            viewModel.prevEnteredFromStateFlow.collect {
                when(it) {
                    is Result.Success -> {
                        adapter.items = it.data.map { from -> TownUiState.Success(from) }.asReversed()
                    }
                    is Result.Loading -> {
                        adapter.items = Collections.nCopies(3, TownUiState.Loading).toList()
                    }
                    is Result.Error -> {
                        Log.e("ErrorFrom", it.exception.stackTraceToString())
                        adapter.items = emptyList()
                        Toast.makeText(requireActivity(), "Проблемы с внутренними файлами", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun initializeComponent(): ChoosingFromFragmentComponent =
        DaggerChoosingFromFragmentComponent.factory()
            .create(this, townClickedSharedViewModel, findDependencies())
}