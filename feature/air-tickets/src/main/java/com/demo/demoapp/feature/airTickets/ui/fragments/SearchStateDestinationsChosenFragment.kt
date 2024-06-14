package com.demo.demoapp.feature.airTickets.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.demo.demoapp.core.common.di.lazyViewModel
import com.demo.demoapp.feature.airTickets.databinding.FragmentSearchStateDestinationsChosenBinding
import com.demo.demoapp.feature.airTickets.di.SearchStateDestinationsChosenFragmentComponent
import com.demo.demoapp.feature.airTickets.ui.viewControllers.SearchStateDestinationsChosenFragmentViewController
import com.demo.demoapp.feature.airTickets.viewModels.SearchStateDestinationsChosenFragmentViewModel

internal class SearchStateDestinationsChosenFragment : Fragment() {
    private var _binding: FragmentSearchStateDestinationsChosenBinding? = null
    private val binding: FragmentSearchStateDestinationsChosenBinding
        get() = _binding!!
    private val args: SearchStateDestinationsChosenFragmentArgs by navArgs()

    internal val searchStateDestinationsChosenFragmentComponent: SearchStateDestinationsChosenFragmentComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): SearchStateDestinationsChosenFragmentComponent =
        (requireParentFragment().requireParentFragment() as ChoosingRouteFragment).choosingRouteFragmentComponent.searchStateDestinationsChosenFragmentComponentFactory()
            .create(this)

    private val viewModel by lazyViewModel<SearchStateDestinationsChosenFragmentViewModel> { stateHandle ->
        searchStateDestinationsChosenFragmentComponent.searchStateDestinationsChosenFragmentViewModel()
            .create(savedStateHandle = stateHandle, from = args.from, to = args.to)
    }


    private lateinit var viewController: SearchStateDestinationsChosenFragmentViewController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchStateDestinationsChosenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchStateDestinationsChosenFragmentComponent.inject(this)
        viewController =
            searchStateDestinationsChosenFragmentComponent.searchStateDestinationsChosenFragmentViewController()
                .create(this, args, binding, viewModel)
        viewController.onCreate()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}