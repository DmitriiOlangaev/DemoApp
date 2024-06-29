package com.demo.demoapp.core.network.di

import android.app.Application
import coil.ImageLoader
import com.demo.demoapp.core.common.di.ApplicationScope
import com.demo.demoapp.core.network.NetworkDataSource
import com.demo.demoapp.core.network.retrofit.RetrofitNetwork
import dagger.Binds
import dagger.Lazy
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient

@Module
abstract class NetworkModule {

    @Binds
    @ApplicationScope
    internal abstract fun networkDataSource(retrofitNetwork: RetrofitNetwork): NetworkDataSource


    companion object {
        @Provides
        @ApplicationScope
        fun providesNetworkJson() : Json = Json

        @Provides
        @ApplicationScope
        fun okHttpCallFactory() : Call.Factory = OkHttpClient.Builder().build()

        @Provides
        @ApplicationScope
        fun imageLoaderView(
            okHttpCallFactory: Lazy<Call.Factory>,
            application: Application) : ImageLoader = ImageLoader.Builder(application).callFactory { okHttpCallFactory.get() }.build()
    }
}