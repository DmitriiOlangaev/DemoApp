plugins {
    id("android-library-convention")
    id("dagger-convention")
}

android {
    namespace = "com.demo.demoapp.domain"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.coil.base)
    implementation(project(":data:model"))
    implementation(project(":data:repositories"))
    implementation(project(":data:datastore"))
}