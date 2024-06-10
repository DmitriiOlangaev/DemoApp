plugins {
    id("android-library-convention")
    id("dagger-convention")
    id("androidx.navigation.safeargs.kotlin")
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-parcelize")
}

android {
    namespace = "com.demo.demoapp.feature.airTickets"
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.bundles.view)
    implementation(libs.bundles.view.navigation)
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:common-android"))
    implementation(project(":core:repositories"))
    implementation(libs.adapterdelegates4.kotlin.dsl)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}