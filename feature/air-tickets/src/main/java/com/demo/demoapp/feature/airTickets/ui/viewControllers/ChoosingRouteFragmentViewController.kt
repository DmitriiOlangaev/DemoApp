package com.demo.demoapp.feature.airTickets.ui.viewControllers

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageButton
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.NavHostFragment
import com.demo.demoapp.core.common.di.setCollector
import com.demo.demoapp.feature.airTickets.R
import com.demo.demoapp.feature.airTickets.SearchStateNavigationDirections
import com.demo.demoapp.feature.airTickets.databinding.FragmentChoosingRouteBinding
import com.demo.demoapp.feature.airTickets.ui.fragments.EnterFragment
import com.demo.demoapp.feature.airTickets.viewModels.ChoosingRouteFragmentViewModel
import com.demo.demoapp.feature.airTickets.viewModels.ToDestinationSharedViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Job

internal class ChoosingRouteFragmentViewController @AssistedInject constructor(
    @Assisted fragment: EnterFragment,
    @Assisted private val binding: FragmentChoosingRouteBinding,
    @Assisted private val viewModel: ChoosingRouteFragmentViewModel,
    @Assisted private val sharedViewModel: ToDestinationSharedViewModel,
    private val adapter: ArrayAdapter<String>
) :
    FragmentViewController<EnterFragment>(fragment) {

    private val navHostFragment
        get() = fragment.childFragmentManager.findFragmentById(R.id.searchStateNavHostFragment) as NavHostFragment
    private var destinationCollectionJob: Job? = null


    override fun onCreate() {
        super.onCreate()
        setUpFromDestination()
        setUpToDestination()
    }


    override fun onStart() {
        super.onStart()
        setUpTownsFlowListener()
        setUpDestinationsListener()
        setUpSearchStateListener()
        setUpDestinationChannelListener()
    }

    private fun setUpDestinationsListener() {
        fragment.setCollector {
            viewModel.fromFlow.collect {
                if (it != binding.fromAutoCompleteTextView.text.toString()) {
                    binding.fromAutoCompleteTextView.setText(it)
                }
            }
        }
        fragment.setCollector {
            viewModel.toFlow.collect {
                if (it != binding.toAutoCompleteTextView.text.toString()) {
                    binding.toAutoCompleteTextView.setText(it)
                }
            }
        }
    }

    private fun setUpTownsFlowListener() {
        fragment.setCollector {
            viewModel.townsFlow.collect {
                adapter.clear()
                adapter.addAll(it)
            }
        }
    }

    private fun setUpDestinationChannelListener() {
        fragment.setCollector {
            for (town in sharedViewModel.toDestinationChannel) {
                binding.toAutoCompleteTextView.setText(town)
                binding.toAutoCompleteTextView.clearFocus()
            }

        }
    }


    private fun setUpSearchStateListener() {
        fragment.setCollector {
            viewModel.searchState.collect {
                if (it.isFromChosen && it.isToChosen) {
                    val action =
                        SearchStateNavigationDirections.actionGlobalSearchStateDestinationsChosenFragment(
                            binding.fromAutoCompleteTextView.text.toString(),
                            binding.toAutoCompleteTextView.text.toString()
                        )
                    navHostFragment.navController.navigate(action)
                } else if (navHostFragment.navController.currentDestination!!.id != R.id.searchStateInitFragment) {
                    navHostFragment.navController.navigate(R.id.searchStateInitFragment)
                }
            }
        }
    }

    private fun setUpDestination(
        autoCompleteTextView: AutoCompleteTextView,
        imageButton: ImageButton,
        onTextChanged: (String) -> Unit,
        onItemClickListener: (AdapterView<*>, View, Int, Long) -> Unit
    ) {
        imageButton.setOnClickListener { autoCompleteTextView.text.clear() }
        autoCompleteTextView.doAfterTextChanged { onTextChanged(it.toString()) }
        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            onItemClickListener(
                parent,
                view,
                position,
                id
            )
        }
        autoCompleteTextView.threshold = 0
    }

    private fun setUpFromDestination() {
        setUpDestination(
            binding.fromAutoCompleteTextView,
            binding.removeFromDestinationButton,
            viewModel::onFromChosen
        ) { parent, _, position, _ ->
            viewModel.onFromChosen(parent.getItemAtPosition(position) as String)
        }
    }

    private fun setUpToDestination() {
        setUpDestination(
            binding.toAutoCompleteTextView,
            binding.removeToDestinationButton,
            viewModel::onToChosen
        )
        { parent, _, position, _ ->
            viewModel.onToChosen(parent.getItemAtPosition(position) as String)
        }
        binding.toAutoCompleteTextView.setOnFocusChangeListener { v, hasFocus ->
            viewModel.toEditTextFocus.value = hasFocus
            navHostFragment.navController.navigate(
                if (hasFocus) R.id.searchStateChoosingToFragment
                else R.id.searchStateInitFragment
            )
        }
    }

    @AssistedFactory
    internal interface Factory {
        fun create(
            choosingRouteFragment: EnterFragment,
            binding: FragmentChoosingRouteBinding,
            viewModel: ChoosingRouteFragmentViewModel,
            sharedViewModel: ToDestinationSharedViewModel
        ): ChoosingRouteFragmentViewController
    }
}