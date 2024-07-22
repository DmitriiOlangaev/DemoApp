@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {
    val implementation by configurations
    val api by configurations
    api(libs.kotlinx.serialization.json)
}

val org.gradle.api.Project.`libs`: org.gradle.accessors.dm.LibrariesForLibs
    get() =
        (this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("libs") as org.gradle.accessors.dm.LibrariesForLibs

fun org.gradle.api.Project.`libs`(configure: Action<org.gradle.accessors.dm.LibrariesForLibs>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("libs", configure)
