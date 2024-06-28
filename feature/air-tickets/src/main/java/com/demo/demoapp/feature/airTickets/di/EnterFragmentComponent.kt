package com.demo.demoapp.feature.airTickets.di

import android.content.Context
import android.widget.ArrayAdapter
import com.demo.demoapp.core.common.di.FragmentScope
import com.demo.demoapp.feature.airTickets.ui.fragments.EnterFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.Channel

@Component(
    dependencies = [AirTicketsDeps::class],
    modules = [EnterFragmentModule::class]
)
@FragmentScope
internal interface EnterFragmentComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance enterFragment: EnterFragment,
            @BindsInstance context: Context,
            deps: AirTicketsDeps
        ): EnterFragmentComponent
    }


    fun inject(enterFragment: EnterFragment)
}

@Module
internal interface EnterFragmentModule {
    companion object {
    }
}

