pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "DemoApp"
include(":app-view")
include(":core:common-android")
include(":core:datasources")
include(":core:model")
include(":core:repositories")
include(":core:common")
include(":feature:air-tickets")
include(":feature:mock")
include(":core:datastore-proto")
include(":core:designsystem")
include(":core:network")
