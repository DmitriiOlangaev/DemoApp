package com.demo.demoapp.view.di

import android.app.Application
import com.demo.demoapp.core.common.di.ApplicationScope
import com.demo.demoapp.core.datasources.di.DataSourcesModule
import com.demo.demoapp.core.datastore.di.DataStoreModule
import com.demo.demoapp.core.network.di.NetworkModule
import com.demo.demoapp.core.repositories.di.RepositoriesModule
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [ApplicationComponentModule::class, DataSourcesModule::class, DataStoreModule::class, RepositoriesModule::class, NetworkModule::class])
internal interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }

    fun mainActivityComponentFactory(): MainActivityComponent.Factory
}