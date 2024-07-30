package com.demo.demoapp.feature.airTickets.di

import com.demo.demoapp.core.common.Dependencies
import com.demo.demoapp.domain.usecases.GetTownsUseCase

internal interface ChoosingTownFragmentDeps : Dependencies {
    val getTownsUseCase: GetTownsUseCase
//    @Dispatcher(AppDispatcher.Default)
//    val dispatcherDefault: CoroutineDispatcher
}