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
include(":core:common-android")
include(":core:common")
include(":core:designsystem")
include(":app-view")
include(":presentation:air-tickets")
include(":presentation:mock")
include(":domain")
include(":data:repositories")
include(":data:network")
include(":data:model")
include(":data:datastore")
