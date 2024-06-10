plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
    id("dagger-convention")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}