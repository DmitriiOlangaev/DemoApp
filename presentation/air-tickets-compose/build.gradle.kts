plugins {
    id("android-library-convention")
    id("hilt-convention")
}

android {
    namespace = "com.demo.demoapp.presentation.air.tickets.compose"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.coil.base)
    implementation(project(":core:common"))
    implementation(project(":core:common-android"))
    implementation(project(":domain"))
    implementation(project(":core:designsystem-compose"))
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation("com.valentinilk.shimmer:compose-shimmer:1.3.0")
    debugImplementation(libs.ui.tooling.preview)
}