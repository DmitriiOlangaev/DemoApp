package com.demo.demoapp.feature.airTickets.viewModels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

internal class EnteredTownSharedViewModel : ViewModel(){
    val enteredTown : MutableStateFlow<String> = MutableStateFlow("")
}