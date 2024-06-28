plugins {
    id("android-library-convention")
}

android {
    namespace = "com.demo.demoapp.designsystem"
}

dependencies {
    implementation(libs.androidx.appcompat)
}
