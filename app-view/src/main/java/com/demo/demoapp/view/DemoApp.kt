package com.demo.demoapp.view

import android.app.Application
import com.demo.demoapp.view.di.ApplicationComponent
import com.demo.demoapp.view.di.DaggerApplicationComponent

internal open class DemoApp : Application() {
    internal val applicationComponent: ApplicationComponent by lazy {
        initializeComponent()
    }

    internal open fun initializeComponent(): ApplicationComponent =
        DaggerApplicationComponent.factory().create(this)

}