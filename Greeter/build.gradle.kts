/*
 * Greeter Module
 * Fresh Design B fixture for feature 087 / US3 — minimal MVI demo
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
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation("org.jetbrains.compose.runtime:runtime:${LocalVersions.compose}")
            implementation("org.jetbrains.compose.foundation:foundation:${LocalVersions.compose}")
            implementation("org.jetbrains.compose.material:material:${LocalVersions.compose}")
            implementation("org.jetbrains.compose.material:material-icons-extended:${LocalVersions.compose}")
            implementation("org.jetbrains.compose.ui:ui:${LocalVersions.compose}")
            implementation("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")
            implementation(libs.coroutines.core)
            implementation("io.codenode:fbpDsl")
        }
        androidMain.dependencies {
            implementation("org.jetbrains.compose.ui:ui-tooling-preview:${LocalVersions.compose}")
        }
        jvmMain.dependencies {
            implementation("io.codenode:preview-api")
        }
    }
}

android {
    namespace = "io.codenode.greeter"
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
