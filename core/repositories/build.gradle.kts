plugins {
    id("android-library-convention")
    id("dagger-convention")
}


android {
    namespace = "com.demo.demoapp.core.repositories"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:datasources"))
    implementation(project(":core:datastore-proto"))
    implementation(libs.kotlinx.coroutines.android)
}
