package com.demo.demoapp.feature.airTickets.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demo.demoapp.feature.airTickets.databinding.FragmentChoosingTicketBinding

internal class ChoosingTicketFragment : Fragment() {
    private var _binding: FragmentChoosingTicketBinding? = null
    internal val binding: FragmentChoosingTicketBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChoosingTicketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}