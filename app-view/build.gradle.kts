plugins {
    id("android-application-convention")
    id("dagger-convention")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.demo.demoapp.view"

    defaultConfig {
        applicationId = "com.demo.demoapp.view"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.bundles.view)
    implementation(libs.bundles.view.navigation)
    implementation(project(":core:common"))
    implementation(project(":core:common-android"))
    implementation(project(":core:datasources"))
    implementation(project(":core:datastore-proto"))
    implementation(project(":core:repositories"))
    implementation(project(":core:network"))
    implementation(project(":feature:mock"))
    implementation(project(":feature:air-tickets"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}