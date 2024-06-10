package com.demo.demoapp.feature.mock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demo.demoapp.view.feature.mock.databinding.FragmentMockBinding

internal class MockFragment : Fragment() {
    private lateinit var binding: FragmentMockBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMockBinding.inflate(inflater, container, false)
        return binding.root
    }
}