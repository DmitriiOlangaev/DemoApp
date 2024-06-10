package com.demo.demoapp.view.di

import com.demo.demoapp.core.common.di.AppDispatcher
import com.demo.demoapp.core.common.di.ApplicationCoroutineScope
import com.demo.demoapp.core.common.di.ApplicationScope
import com.demo.demoapp.core.common.di.Dispatcher
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Module
internal interface ApplicationComponentModule {
    companion object {
        @Provides
        @ApplicationScope
        @Dispatcher(AppDispatcher.IO)
        fun dispatcherIO(): CoroutineDispatcher = Dispatchers.IO

        @Provides
        @ApplicationScope
        @Dispatcher(AppDispatcher.Default)
        fun dispatcherDefault(): CoroutineDispatcher = Dispatchers.Default

        @Provides
        @ApplicationScope
        @ApplicationCoroutineScope
        fun applicationCoroutineScope(@Dispatcher(AppDispatcher.Default) defaultDispatcher: CoroutineDispatcher): CoroutineScope =
            CoroutineScope(defaultDispatcher + SupervisorJob())
    }
}