package com.demo.demoapp.feature.airTickets.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demo.demoapp.core.common.di.findDependencies
import com.demo.demoapp.core.common.di.lazyViewModel
import com.demo.demoapp.feature.airTickets.databinding.FragmentChoosingRouteBinding
import com.demo.demoapp.feature.airTickets.di.ChoosingRouteFragmentComponent
import com.demo.demoapp.feature.airTickets.di.DaggerChoosingRouteFragmentComponent
import com.demo.demoapp.feature.airTickets.ui.viewControllers.ChoosingRouteFragmentViewController
import com.demo.demoapp.feature.airTickets.viewModels.ChoosingRouteFragmentViewModel
import com.demo.demoapp.feature.airTickets.viewModels.ToDestinationSharedViewModel

internal class ChoosingRouteFragment : Fragment() {
    private var _binding: FragmentChoosingRouteBinding? = null
    private val binding: FragmentChoosingRouteBinding
        get() = _binding!!


    internal val choosingRouteFragmentComponent: ChoosingRouteFragmentComponent by lazy {
        initializeComponent()
    }
    private val viewModel by lazyViewModel<ChoosingRouteFragmentViewModel> { stateHandle ->
        choosingRouteFragmentComponent.choosingRouteFragmentViewModel().create(stateHandle)
    }
    private val sharedViewModel by lazyViewModel<ToDestinationSharedViewModel> { stateHandle ->
        choosingRouteFragmentComponent.toDestinationSharedViewModel()
            .create(stateHandle)
    }

    private fun initializeComponent(): ChoosingRouteFragmentComponent =
        DaggerChoosingRouteFragmentComponent.factory()
            .create(this, requireActivity(), findDependencies())

    private lateinit var viewController: ChoosingRouteFragmentViewController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChoosingRouteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        choosingRouteFragmentComponent.inject(this)
        viewController = choosingRouteFragmentComponent.choosingRouteFragmentViewController()
            .create(this, binding, viewModel, sharedViewModel)
        viewController.onCreate()
    }

    override fun onStart() {
        super.onStart()
        viewController.onStart()
    }


    override fun onDestroyView() {
        _binding = null
        viewController.onDestroy()
        super.onDestroyView()
    }
}