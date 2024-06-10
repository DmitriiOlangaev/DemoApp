plugins {
    id("android-library-convention")
    id("dagger-convention")
}

android {
    namespace = "com.demo.demoapp.core.common"
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.fragment.ktx)
}