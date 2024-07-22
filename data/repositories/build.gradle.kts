plugins {
    id("android-library-convention")
    id("dagger-convention")
}


android {
    namespace = "com.demo.demoapp.data.repositories"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":data:model"))
    implementation(project(":data:network"))
    implementation(libs.kotlinx.coroutines.android)
}
