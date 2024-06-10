@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 34

    defaultConfig {
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}


val com.android.build.gradle.internal.dsl.BaseAppModuleExtension.`kotlinOptions`: org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
    get() =
        (this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("kotlinOptions") as org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

/**
 * Configures the [kotlinOptions][org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions] extension.
 */
fun com.android.build.gradle.internal.dsl.BaseAppModuleExtension.`kotlinOptions`(configure: Action<org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("kotlinOptions", configure)

val org.gradle.api.Project.`android`: com.android.build.gradle.internal.dsl.BaseAppModuleExtension
    get() =
        (this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("android") as com.android.build.gradle.internal.dsl.BaseAppModuleExtension

/**
 * Configures the [android][com.android.build.gradle.internal.dsl.BaseAppModuleExtension] extension.
 */
fun org.gradle.api.Project.`android`(configure: Action<com.android.build.gradle.internal.dsl.BaseAppModuleExtension>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("android", configure)
