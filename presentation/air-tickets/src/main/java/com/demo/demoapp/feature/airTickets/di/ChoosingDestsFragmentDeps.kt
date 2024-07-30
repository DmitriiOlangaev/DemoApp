package com.demo.demoapp.feature.airTickets.di

import com.demo.demoapp.core.common.Dependencies
import com.demo.demoapp.domain.usecases.EnterFromUseCase
import com.demo.demoapp.domain.usecases.EnterToUseCase

internal interface ChoosingDestsFragmentDeps : ChoosingTownFragmentDeps, ChoosingFromFragmentDeps, ChoosingToFragmentDeps, Dependencies{
    val enterFromUseCase : EnterFromUseCase
    val enterToUseCase : EnterToUseCase
//    val getPreviouslyEnteredFromUseCase : GetPreviouslyEnteredFromUseCase
}