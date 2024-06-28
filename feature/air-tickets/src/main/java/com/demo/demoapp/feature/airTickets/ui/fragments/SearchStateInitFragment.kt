package com.demo.demoapp.feature.airTickets.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demo.demoapp.core.common.di.lazyViewModel
import com.demo.demoapp.feature.airTickets.databinding.FragmentSearchStateInitBinding
import com.demo.demoapp.feature.airTickets.di.SearchStateInitFragmentComponent
import com.demo.demoapp.feature.airTickets.ui.viewControllers.SearchStateInitFragmentViewController
import com.demo.demoapp.feature.airTickets.viewModels.SearchStateInitFragmentViewModel

internal class SearchStateInitFragment : Fragment() {
    private var _binding: FragmentSearchStateInitBinding? = null
    private val binding: FragmentSearchStateInitBinding
        get() = _binding!!
    internal val searchStateInitFragmentComponent: SearchStateInitFragmentComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): SearchStateInitFragmentComponent =
        (requireParentFragment().parentFragment as EnterFragment).choosingRouteFragmentComponent.searchStateInitFragmentComponentFactory()
            .create(this)

    private val viewModel by lazyViewModel<SearchStateInitFragmentViewModel> { stateHandle ->
        searchStateInitFragmentComponent.searchStateInitFragmentViewModel().create(stateHandle)
    }
    private lateinit var viewController: SearchStateInitFragmentViewController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchStateInitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchStateInitFragmentComponent.inject(this)
        viewController = searchStateInitFragmentComponent.searchStateInitFragmentViewController()
            .create(this, binding, viewModel)
        viewController.onCreate()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
