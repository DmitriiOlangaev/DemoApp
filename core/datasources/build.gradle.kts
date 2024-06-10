plugins {
    alias(libs.plugins.jetbrainsKotlinJvm)
    id("java-library")
    id("dagger-convention")
    id("kotlin-serialization-convention")
}


java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(libs.kotlinx.coroutines.android)
}
