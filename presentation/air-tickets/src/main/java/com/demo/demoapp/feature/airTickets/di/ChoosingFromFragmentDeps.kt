package com.demo.demoapp.feature.airTickets.di

import com.demo.demoapp.core.common.Dependencies
import com.demo.demoapp.domain.usecases.GetPreviouslyEnteredFromUseCase

internal interface ChoosingFromFragmentDeps : Dependencies {
    val getPreviouslyEnteredFromUseCase : GetPreviouslyEnteredFromUseCase
}