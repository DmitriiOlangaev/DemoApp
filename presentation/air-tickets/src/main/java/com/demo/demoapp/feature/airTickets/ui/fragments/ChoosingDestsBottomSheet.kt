package com.demo.demoapp.feature.airTickets.ui.fragments

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.demo.demoapp.core.common.DepsMap
import com.demo.demoapp.core.common.HasDependencies
import com.demo.demoapp.core.common.Result
import com.demo.demoapp.core.common.findDependencies
import com.demo.demoapp.core.common.lazyViewModel
import com.demo.demoapp.core.common.setCollector
import com.demo.demoapp.feature.airTickets.R
import com.demo.demoapp.feature.airTickets.databinding.FragmentChoosingDestsBinding
import com.demo.demoapp.feature.airTickets.di.ChoosingDestsFragmentComponent
import com.demo.demoapp.feature.airTickets.di.DaggerChoosingDestsFragmentComponent
import com.demo.demoapp.feature.airTickets.ui.navArgs.ChosenDestination
import com.demo.demoapp.feature.airTickets.viewModels.ChoosingDestsFragmentViewModel
import com.demo.demoapp.feature.airTickets.viewModels.EnteredTownSharedViewModel
import com.demo.demoapp.feature.airTickets.viewModels.TownClickedSharedViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

internal class ChoosingDestsBottomSheet : BottomSheetDialogFragment(), HasDependencies {
    @Inject
    override lateinit var depsMap: DepsMap
    private var _binding: FragmentChoosingDestsBinding? = null
    private val binding: FragmentChoosingDestsBinding
        get() = _binding!!
    private val args by navArgs<ChoosingDestsBottomSheetArgs>()

    private val navHostFragment
        get() = childFragmentManager.findFragmentById(R.id.nhf_choosing_dests) as NavHostFragment

    private val component: ChoosingDestsFragmentComponent by lazy {
        initializeComponent()
    }

    private val enterTownViewModel: EnteredTownSharedViewModel by viewModels()

    private val townClickedViewModel: TownClickedSharedViewModel by viewModels()

    private val viewModel: ChoosingDestsFragmentViewModel by lazyViewModel { stateHandle ->
        component.viewModelFactory().create(stateHandle)
    }

    private var ignoreDestEtEdited: Boolean = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChoosingDestsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpDialog()
        handleNavArgs()
        setPrevEnteredFromCollector()
        setUpEtDest(binding.etFrom, R.id.choosingFromFragment, binding.btnClearFrom)
        setUpEtDest(binding.etTo, R.id.choosingToFragment, binding.btnClearTo)
        setClickedTownsCollector()
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initializeComponent(): ChoosingDestsFragmentComponent =
        DaggerChoosingDestsFragmentComponent.factory()
            .create(this, findDependencies())

    private fun setUpDialog() {
        binding.clBottomSheetChoosingDestsRoot.minimumHeight =
            (Resources.getSystem().displayMetrics.heightPixels)
        (dialog as BottomSheetDialog).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.skipCollapsed = true
            addHideKeyboardOnSLideCallback(behavior)
            window?.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
                setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
            }
        }
    }


    private fun setUpEtDest(et: EditText, destID: Int, btn: ImageButton) {
        setFocusChangeListener(et, destID)
        setTextChangedListener(et, destID)
        setClearTextButton(et, btn)
    }

    private fun EditText.setTextOnDest(text : String) {
        ignoreDestEtEdited = true
        setText(text)
        selectAll()
        ignoreDestEtEdited = false
    }

    private fun setClickedTownsCollector() {
        setCollector {
            for (town in townClickedViewModel.channel) {
                val et: EditText
                val callback: (String) -> Unit
                if (binding.etFrom.isFocused) {
                    et = binding.etFrom
                    callback = viewModel::enterFrom
                } else if (binding.etTo.isFocused) {
                    et = binding.etTo
                    callback = viewModel::enterTo
                } else continue
                et.setTextOnDest(town)
                callback(town)
            }
        }
    }

    private fun setPrevEnteredFromCollector() {
        setCollector {
            viewModel.fromStateFlow.collect {
                when (it) {
                    is Result.Success -> {
                        binding.etFrom.setTextOnDest(it.data)
                    }

                    else -> Unit
                }

            }
        }
    }

    private fun setClearTextButton(et: EditText, btn: ImageButton) {
        btn.setOnClickListener {
            et.text.clear()
        }
    }

    private fun setTextChangedListener(et: EditText, destID: Int) {
        et.doOnTextChanged { text, start, count, after ->
            if (ignoreDestEtEdited || text == null) return@doOnTextChanged
            enterTownViewModel.enteredTown.value = text.toString()
            if (text.isEmpty()) {
                navHostFragment.navController.navigate(destID)
            } else if (navHostFragment.navController.currentDestination?.id != R.id.choosingTownFragment) {
                navHostFragment.navController.navigate(R.id.choosingTownFragment)
            }
        }
    }

    private fun setFocusChangeListener(et: EditText, destID: Int) {
        et.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                navHostFragment.navController.navigate(destID)
                onEtChosen(et)
            } else {
                et.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.ic_plane_up
                    ), null, null, null
                )
            }
        }
    }

    private fun addHideKeyboardOnSLideCallback(behavior: BottomSheetBehavior<FrameLayout>) {
        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(p0: View, p1: Int) {}

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                if (slideOffset > 0) {
                    val inputMethodManager =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(bottomSheet.windowToken, 0)
                }
            }

        })
    }


    private fun handleNavArgs() {
        val navGraph =
            navHostFragment.navController.navInflater.inflate(R.navigation.choosing_dests_navigation)
        when (args.chosenDestination) {
            ChosenDestination.FROM -> {
                binding.etFrom.requestFocus()
                onEtChosen(binding.etFrom)
                navGraph.setStartDestination(R.id.choosingFromFragment)
            }

            ChosenDestination.TO -> {
                binding.etTo.requestFocus()
                onEtChosen(binding.etTo)
                navGraph.setStartDestination(R.id.choosingToFragment)
            }
        }
        navHostFragment.navController.graph = navGraph
    }

    private fun onEtChosen(et: EditText) {
        et.setCompoundDrawablesRelativeWithIntrinsicBounds(
            ContextCompat.getDrawable(
                requireActivity(),
                R.drawable.ic_search
            ), null, null, null
        )
    }
}