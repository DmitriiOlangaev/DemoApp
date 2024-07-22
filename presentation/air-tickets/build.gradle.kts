plugins {
    id("android-library-convention")
    id("dagger-convention")
    id("androidx.navigation.safeargs")
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
    implementation(libs.coil.base)
    implementation(project(":domain"))
    implementation(project(":core:common"))
    implementation(project(":core:common-android"))
    implementation(project(":core:designsystem"))
    implementation(libs.adapterdelegates4.kotlin.dsl)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}