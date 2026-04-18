/*
 * TestFolder Module
 * Demo UI sandbox for previewing Compose composables in Android Studio
 * License: Apache 2.0
 */

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.android.library)
}

object LocalVersions {
    const val compose = "1.7.3"
}

kotlin {
    androidTarget {
        compilations.all {
            compilerOptions.configure {
                jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation("org.jetbrains.compose.runtime:runtime:${LocalVersions.compose}")
            implementation("org.jetbrains.compose.foundation:foundation:${LocalVersions.compose}")
            implementation("org.jetbrains.compose.material:material:${LocalVersions.compose}")
            implementation("org.jetbrains.compose.material:material-icons-extended:${LocalVersions.compose}")
            implementation("org.jetbrains.compose.ui:ui:${LocalVersions.compose}")
        }
        androidMain.dependencies {
            implementation("org.jetbrains.compose.ui:ui-tooling-preview:${LocalVersions.compose}")
        }
    }
}

android {
    namespace = "io.codenode.demo"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    dependencies {
        debugImplementation("org.jetbrains.compose.ui:ui-tooling:${LocalVersions.compose}")
    }
}
