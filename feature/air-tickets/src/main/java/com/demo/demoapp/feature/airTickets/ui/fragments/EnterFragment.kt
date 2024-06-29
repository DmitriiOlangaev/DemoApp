package com.demo.demoapp.feature.airTickets.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.demo.demoapp.core.common.findDependencies
import com.demo.demoapp.feature.airTickets.databinding.FragmentEnterBinding
import com.demo.demoapp.feature.airTickets.di.EnterFragmentComponent
import com.demo.demoapp.feature.airTickets.di.DaggerEnterFragmentComponent

internal class EnterFragment : Fragment() {
    private var _binding: FragmentEnterBinding? = null
    private val binding: FragmentEnterBinding
        get() = _binding!!


    private val enterFragmentComponent: EnterFragmentComponent by lazy {
        initializeComponent()
    }
//    private val viewModel by lazyViewModel<EnterFragmentViewModel> { stateHandle ->
//        enterFragmentComponent.viewModel().create(stateHandle)
//    }

    private fun initializeComponent(): EnterFragmentComponent =
        DaggerEnterFragmentComponent.factory()
            .create(this, requireActivity(), findDependencies())



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enterFragmentComponent.inject(this)
        binding.btnFrom.setOnClickListener {
            Toast.makeText(requireContext(), "hui", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}