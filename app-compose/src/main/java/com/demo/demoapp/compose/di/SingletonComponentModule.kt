package com.demo.demoapp.compose.di

import com.demo.demoapp.core.common.di.AppDispatcher
import com.demo.demoapp.core.common.di.ApplicationCoroutineScope
import com.demo.demoapp.core.common.di.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface SingletonComponentModule {
    companion object {
        @Provides
        @Singleton
        @Dispatcher(AppDispatcher.IO)
        fun getDispatcherIO(): CoroutineDispatcher = Dispatchers.IO

        @Provides
        @Dispatcher(AppDispatcher.Default)
        fun getDispatcherDefault(): CoroutineDispatcher = Dispatchers.Default

        @Provides
        @ApplicationCoroutineScope
        fun applicationCoroutineScope(@Dispatcher(AppDispatcher.Default) defaultDispatcher: CoroutineDispatcher): CoroutineScope =
            CoroutineScope(defaultDispatcher + SupervisorJob())
    }
}
