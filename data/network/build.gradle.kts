plugins {
    id("android-library-convention")
    id("dagger-convention")
    id("kotlin-serialization-convention")
}

android {
    namespace = "com.demo.demoapp.data.network"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":data:model"))
    api(libs.coil.base)
    api(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
}