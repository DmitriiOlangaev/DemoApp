package com.demo.demoapp.core.common.di

import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class DependenciesKey(val value: KClass<out Dependencies>)
