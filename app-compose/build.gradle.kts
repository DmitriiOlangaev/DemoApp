plugins {
    id("android-application-convention")
    id("hilt-convention")
}

android {
    namespace = "com.demo.demoapp.compose"

    defaultConfig {
        applicationId = "com.demo.demoapp.compose"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
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
    implementation(project(":core:common"))
    implementation(project(":core:common-android"))
    implementation(project(":domain"))
    implementation(project(":data:repositories"))
    implementation(project(":data:network"))
    implementation(project(":data:datastore"))
    implementation(project(":presentation:air-tickets-compose"))
    implementation(project(":presentation:mock-compose"))
    implementation(project(":core:designsystem-compose"))
    debugImplementation(libs.ui.tooling.preview)
}