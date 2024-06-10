package com.demo.demoapp.core.datastore.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.demo.demoapp.core.common.di.AppDispatcher
import com.demo.demoapp.core.common.di.ApplicationCoroutineScope
import com.demo.demoapp.core.common.di.ApplicationScope
import com.demo.demoapp.core.common.di.Dispatcher
import com.demo.demoapp.core.datastore.UserPreferences
import com.demo.demoapp.core.datastore.UserPreferencesSerializer
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

@Module
interface DataStoreModule {
    companion object {
        @Provides
        @ApplicationScope
        internal fun providesEnteredDestinationsDataStore(
            context: Application,
            @Dispatcher(AppDispatcher.IO) ioDispatcher: CoroutineDispatcher,
            @ApplicationCoroutineScope scope: CoroutineScope,
            userPreferencesSerializer: UserPreferencesSerializer
        ): DataStore<UserPreferences> =
            DataStoreFactory.create(
                serializer = userPreferencesSerializer,
                scope = CoroutineScope(scope.coroutineContext + ioDispatcher)
            ) {
                context.dataStoreFile("user_preferences.pb")
            }
    }
}