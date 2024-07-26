package com.demo.demoapp.data.datastore.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.demo.demoapp.core.common.di.AppDispatcher
import com.demo.demoapp.core.common.di.ApplicationCoroutineScope
import com.demo.demoapp.core.common.di.Dispatcher
import com.demo.demoapp.data.datastore.PreferencesDataSource
import com.demo.demoapp.data.datastore.PreferencesDataStore
import com.demo.demoapp.data.datastore.UserPreferences
import com.demo.demoapp.data.datastore.UserPreferencesSerializer
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesDataSourceModule {
    companion object {
        @Provides
        @Singleton
        internal fun preferencesDataStore(
            application: Application,
            @Dispatcher(AppDispatcher.IO) ioDispatcher: CoroutineDispatcher,
            @ApplicationCoroutineScope scope: CoroutineScope,
            userPreferencesSerializer: UserPreferencesSerializer

        ): DataStore<UserPreferences> =
            DataStoreFactory.create(
                serializer = userPreferencesSerializer,
                scope = CoroutineScope(scope.coroutineContext + ioDispatcher)
            ) {
                application.dataStoreFile("user_preferences.pb")
            }
    }

    @Binds
    internal abstract fun bindPreferencesDataSource(preferencesDataStore: PreferencesDataStore): PreferencesDataSource
}