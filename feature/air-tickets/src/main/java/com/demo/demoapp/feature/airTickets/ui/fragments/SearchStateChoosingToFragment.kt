package com.demo.demoapp.feature.airTickets.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.demo.demoapp.core.common.di.lazyViewModel
import com.demo.demoapp.feature.airTickets.databinding.FragmentSearchStateChoosingToBinding
import com.demo.demoapp.feature.airTickets.di.SearchStateChoosingToFragmentComponent
import com.demo.demoapp.feature.airTickets.ui.viewControllers.SearchStateChoosingToFragmentViewController
import com.demo.demoapp.feature.airTickets.viewModels.SearchStateChoosingToFragmentViewModel
import com.demo.demoapp.feature.airTickets.viewModels.ToDestinationSharedViewModel

internal class SearchStateChoosingToFragment : Fragment() {

    private var _binding: FragmentSearchStateChoosingToBinding? = null
    private val binding: FragmentSearchStateChoosingToBinding
        get() = _binding!!

    internal val searchStateChoosingToFragmentComponent: SearchStateChoosingToFragmentComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): SearchStateChoosingToFragmentComponent =
        (requireParentFragment().requireParentFragment() as EnterFragment).choosingRouteFragmentComponent.searchStateChoosingToFragmentComponentFactory()
            .create(this)

    private val viewModel by lazyViewModel<SearchStateChoosingToFragmentViewModel> { stateHandle ->
        searchStateChoosingToFragmentComponent.searchStateChoosingToFragmentViewModel()
            .create(stateHandle)
    }

    private val sharedViewModel by viewModels<ToDestinationSharedViewModel>({ requireParentFragment().requireParentFragment() })


    private lateinit var viewController: SearchStateChoosingToFragmentViewController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchStateChoosingToBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchStateChoosingToFragmentComponent.inject(this)
        viewController =
            searchStateChoosingToFragmentComponent.searchStateChoosingToFragmentViewController()
                .create(this, binding, viewModel, sharedViewModel)
        viewController.onCreate()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}