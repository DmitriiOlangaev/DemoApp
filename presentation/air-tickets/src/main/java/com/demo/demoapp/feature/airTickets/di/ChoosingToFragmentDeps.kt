package com.demo.demoapp.feature.airTickets.di

import com.demo.demoapp.core.common.Dependencies
import com.demo.demoapp.domain.usecases.GetToDestinationsUseCase

internal interface ChoosingToFragmentDeps : Dependencies {
    val getToDestinationsUseCase : GetToDestinationsUseCase
}