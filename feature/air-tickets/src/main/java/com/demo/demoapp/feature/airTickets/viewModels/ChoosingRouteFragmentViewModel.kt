package com.demo.demoapp.feature.airTickets.viewModels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.demoapp.core.repositories.interfaces.TownsRepository
import com.demo.demoapp.core.repositories.interfaces.UserDataRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

internal class ChoosingRouteFragmentViewModel @AssistedInject constructor(
    @Assisted stateHandle: SavedStateHandle,
    private val townsRepository: TownsRepository,
    private val userDataRepository: UserDataRepository
) : ViewModel() {
    internal data class SearchState(val isFromChosen: Boolean, val isToChosen: Boolean)

    internal val townsFlow =
        townsRepository.getTowns().stateIn(viewModelScope, SharingStarted.Lazily, listOf())
    internal val fromFlow = userDataRepository.getUserData().map { it.enteredFromDest }
        .stateIn(viewModelScope, SharingStarted.Lazily, "")
    internal val toFlow = userDataRepository.getUserData().map { it.enteredToDest }
        .stateIn(viewModelScope, SharingStarted.Lazily, "")
    private val _searchState =
        MutableStateFlow(SearchState(isFromChosen = false, isToChosen = false))
    val searchState = _searchState
    private val _fromEditTextFocus: MutableStateFlow<Boolean> = MutableStateFlow(false)
    internal val fromEditTextFocus = _fromEditTextFocus
    private val _toEditTextFocus: MutableStateFlow<Boolean> = MutableStateFlow(false)
    internal val toEditTextFocus = _toEditTextFocus


    fun onFromChosen(from: String) {
        _searchState.value = _searchState.value.copy(isFromChosen = townsFlow.value.contains(from))
        if (_searchState.value.isFromChosen) {
            _searchState.value = _searchState.value.copy(isFromChosen = true)
            viewModelScope.launch {
                Log.d("DemoApp", "userdatarepository")
                userDataRepository.setFrom(from)
            }
        }
    }

    fun onToChosen(to: String) {
        _searchState.value = _searchState.value.copy(isToChosen = townsFlow.value.contains(to))
        if (_searchState.value.isToChosen) {
            _searchState.value = _searchState.value.copy(isToChosen = true)
            viewModelScope.launch {
                userDataRepository.setTo(to)
            }
        }
    }

    @AssistedFactory
    internal interface Factory {
        fun create(savedStateHandle: SavedStateHandle): ChoosingRouteFragmentViewModel
    }

}