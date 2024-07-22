package com.demo.demoapp.view.di

import android.app.Application
import com.demo.demoapp.core.common.di.AppDispatcher
import com.demo.demoapp.core.common.di.ApplicationCoroutineScope
import com.demo.demoapp.core.common.di.ApplicationScope
import com.demo.demoapp.core.common.di.Dispatcher
import com.demo.demoapp.data.datastore.di.PreferencesDataSourceModule
import com.demo.demoapp.data.network.di.NetworkModule
import com.demo.demoapp.data.repositories.di.RepositoriesModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@ApplicationScope
@Component(modules = [ApplicationComponentModule::class, RepositoriesModule::class, NetworkModule::class, PreferencesDataSourceModule::class])
internal interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }

    fun getMainActivityComponentFactory(): MainActivityComponent.Factory
}

@Module
internal interface ApplicationComponentModule {
    companion object {
        @Provides
        @ApplicationScope
        @Dispatcher(AppDispatcher.IO)
        fun getDispatcherIO(): CoroutineDispatcher = Dispatchers.IO

        @Provides
        @ApplicationScope
        @Dispatcher(AppDispatcher.Default)
        fun getDispatcherDefault(): CoroutineDispatcher = Dispatchers.Default

        @Provides
        @ApplicationScope
        @ApplicationCoroutineScope
        fun applicationCoroutineScope(@Dispatcher(AppDispatcher.Default) defaultDispatcher: CoroutineDispatcher): CoroutineScope =
            CoroutineScope(defaultDispatcher + SupervisorJob())
    }
}
