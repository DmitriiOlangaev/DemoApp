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
    implementation(project(":domain"))
    implementation(project(":data:repositories"))
    implementation(project(":data:network"))
    implementation(project(":data:datastore"))
    implementation(project(":presentation:mock"))
    implementation(project(":presentation:air-tickets"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}