plugins {
    id("android-library-convention")
    id("dagger-convention")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.demo.demoapp.view.feature.mock"
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.bundles.view)
    implementation(libs.bundles.view.navigation)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}