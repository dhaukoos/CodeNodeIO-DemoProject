/*
 * CodeNodeIO-DemoProject
 * Root build configuration for demonstration project
 * License: Apache 2.0
 */

plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.compose) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
}

// Common version constraints across all modules
allprojects {
    group = "io.codenode"
    version = "0.1.0-SNAPSHOT"
}

/**
 * Launches the CodeNodeIO graphEditor with all project modules on the classpath.
 *
 * This delegates to the launcher module's Compose Desktop `run` task, which
 * handles macOS-specific JVM initialization (AWT, native libraries) correctly.
 *
 * Usage: ./gradlew runGraphEditor
 */
tasks.register("runGraphEditor") {
    description = "Launch CodeNodeIO graphEditor with all project modules"
    group = "application"
    dependsOn(":launcher:run")
}
