import org.gradle.accessors.dm.LibrariesForLibs

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

dependencies {
    val implementation by configurations
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}

fun DependencyHandler.`ksp`(dependencyNotation: Any): Dependency? =
    add("ksp", dependencyNotation)

val org.gradle.api.Project.`libs`: org.gradle.accessors.dm.LibrariesForLibs
    get() =
        (this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("libs") as org.gradle.accessors.dm.LibrariesForLibs

fun org.gradle.api.Project.`libs`(configure: Action<LibrariesForLibs>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("libs", configure)
