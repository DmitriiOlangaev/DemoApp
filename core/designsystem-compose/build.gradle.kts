plugins {
    id("android-library-convention")
}

android {
    namespace = "com.demo.demoapp.core.designsystem.compose"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    api(libs.material3)
}