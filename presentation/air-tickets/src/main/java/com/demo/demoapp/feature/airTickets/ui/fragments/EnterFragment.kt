package com.demo.demoapp.feature.airTickets.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.demoapp.core.common.Result
import com.demo.demoapp.core.common.findDependencies
import com.demo.demoapp.core.common.lazyViewModel
import com.demo.demoapp.core.common.setCollector
import com.demo.demoapp.feature.airTickets.databinding.FragmentEnterBinding
import com.demo.demoapp.feature.airTickets.di.DaggerEnterFragmentComponent
import com.demo.demoapp.feature.airTickets.di.EnterFragmentComponent
import com.demo.demoapp.feature.airTickets.ui.navArgs.ChosenDestination
import com.demo.demoapp.feature.airTickets.ui.recyclerView.ConcertUiState
import com.demo.demoapp.feature.airTickets.viewModels.EnterFragmentViewModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import java.util.Collections
import javax.inject.Inject

internal class EnterFragment : Fragment() {
    private var _binding: FragmentEnterBinding? = null
    private val binding: FragmentEnterBinding
        get() = _binding!!


    private val component: EnterFragmentComponent by lazy {
        initializeComponent()
    }
    private val viewModel by lazyViewModel<EnterFragmentViewModel> { stateHandle ->
        component.viewModelFactory().create(stateHandle)
    }

    @Inject
    internal lateinit var adapter: AsyncListDifferDelegationAdapter<ConcertUiState>


    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRV()
        setUpFromBtn()
        setUpToBtn()
        setConcertsCollector()
        setFromCollector()
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


    private fun initializeComponent(): EnterFragmentComponent =
        DaggerEnterFragmentComponent.factory()
            .create(this, findDependencies())

    private fun setUpRV() {
        binding.rvConcerts.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            adapter = this@EnterFragment.adapter
        }
    }

    private fun setUpFromBtn() {
        setUpDestBtn(binding.btnFrom, ChosenDestination.FROM)
    }

    private fun setUpToBtn() {
        setUpDestBtn(binding.btnTo, ChosenDestination.TO)
    }

    private fun setUpDestBtn(button: Button, chosenDesitnation: ChosenDestination) {
        button.setOnClickListener {
            findNavController().navigate(
                EnterFragmentDirections.actionEnterFragmentToChoosingDestsBottomSheet(
                    chosenDesitnation
                )
            )
        }
    }

    private fun setConcertsCollector() {
        setCollector {
            viewModel.concertsStateFlow.collect {
                when (it) {
                    is Result.Success -> {
                        adapter.items = it.data.map { concert -> ConcertUiState.Success(concert) }
                    }
                    is Result.Loading -> {
                        adapter.items = Collections.nCopies(3, ConcertUiState.Loading).toList()
                    }
                    is Result.Error -> {
                        Log.e("ErrorConcert", it.exception.stackTraceToString())
                        adapter.items = emptyList()
                        Toast.makeText(requireActivity(), "Проблемы с соединением", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setFromCollector() {
        setCollector {
            viewModel.fromStateFlow.collect {
                when (it) {
                    is Result.Success -> {
                        if(it.data.isNotEmpty()) {
                            binding.btnFrom.text = it.data
                            binding.btnFrom.setTextAppearance(com.demo.demoapp.designsystem.R.style.TextAppearance_AppCompat_button1)
                        }
                    }
                    else -> Unit
                }
            }
        }
    }
}