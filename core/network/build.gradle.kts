plugins {
    id("android-library-convention")
    id("dagger-convention")
    id("kotlin-serialization-convention")
}

android {
    namespace = "com.demo.demoapp.core.network"
}

dependencies {
    implementation(project(":core:common"))
    implementation(libs.coil.base)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
}