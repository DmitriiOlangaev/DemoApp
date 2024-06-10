@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.google.devtools.ksp")
}

dependencies {
    val implementation by configurations
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}

fun DependencyHandler.`ksp`(dependencyNotation: Any): Dependency? =
    add("ksp", dependencyNotation)

val org.gradle.api.Project.`libs`: org.gradle.accessors.dm.LibrariesForLibs
    get() =
        (this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("libs") as org.gradle.accessors.dm.LibrariesForLibs

fun org.gradle.api.Project.`libs`(configure: Action<org.gradle.accessors.dm.LibrariesForLibs>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("libs", configure)

