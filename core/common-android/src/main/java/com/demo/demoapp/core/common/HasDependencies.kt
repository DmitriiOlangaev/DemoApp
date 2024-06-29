package com.demo.demoapp.core.common

typealias DepsMap = Map<Class<out Dependencies>, Dependencies>

interface HasDependencies {
    val depsMap: DepsMap
}