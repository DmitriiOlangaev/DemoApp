package com.demo.demoapp.data.network.di

import android.app.Application
import coil.ImageLoader
import coil.request.ImageRequest
import com.demo.demoapp.data.network.NetworkDataSource
import com.demo.demoapp.data.network.retrofit.RetrofitNetwork
import dagger.Binds
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    @Singleton
    internal abstract fun networkDataSource(retrofitNetwork: RetrofitNetwork): NetworkDataSource


    companion object {
        @Provides
        @Singleton
        fun providesNetworkJson(): Json = Json

        @Provides
        @Singleton
        fun okHttpCallFactory(): Call.Factory = OkHttpClient.Builder().build()

        @Provides
        @Singleton
        fun imageLoaderView(
            okHttpCallFactory: Lazy<Call.Factory>,
            application: Application
        ): ImageLoader =
            ImageLoader.Builder(application).callFactory { okHttpCallFactory.get() }.build()

        @Provides
        @Singleton
        fun imageRequestBuilder(application: Application) = ImageRequest.Builder(application)
    }
}