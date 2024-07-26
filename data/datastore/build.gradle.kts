import com.google.protobuf.gradle.GenerateProtoTask

plugins {
    id("android-library-convention")
    id("dagger-convention")
    id("hilt-convention")
    alias(libs.plugins.protobuf)
}

android {
    namespace = "com.demo.demoapp.data.datastore"
}

dependencies {
    implementation(project(":data:model"))
    implementation(project(":core:common"))
    implementation(libs.protobuf.kotlin.lite)
    api(libs.androidx.dataStore.core)
    implementation(libs.kotlinx.coroutines.android)
}

protobuf {
    protoc {
        artifact = libs.protobuf.protoc.get().toString()
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                register("java") {
                    option("lite")
                }
                register("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

androidComponents.beforeVariants {
    android.sourceSets.getByName(it.name) {
        val buildDir = layout.buildDirectory.get().asFile
        java.srcDir(buildDir.resolve("generated/source/proto/${it.name}/java"))
        kotlin.srcDir(buildDir.resolve("generated/source/proto/${it.name}/kotlin"))
    }
}
androidComponents {
    onVariants(selector().all()) { variant ->
        afterEvaluate {
            // This is a workaround for https://issuetracker.google.com/301244513 which depends on internal
            // implementations of the android gradle plugin and the ksp gradle plugin which might change in the future
            // in an unpredictable way.
            val kspTaskName = "ksp${variant.name.capitalize()}Kotlin"
            val kspTask =
                project.tasks.getByName(kspTaskName) as org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompileTool<*>
            val protoTask =
                project.tasks.getByName("generate${variant.name.capitalize()}Proto") as GenerateProtoTask
            kspTask.dependsOn(protoTask)
        }
    }
}
