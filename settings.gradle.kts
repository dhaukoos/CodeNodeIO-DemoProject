/*
 * CodeNodeIO-DemoProject
 * Demonstration project created with CodeNodeIO
 * License: Apache 2.0
 */

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        kotlin("jvm") version "2.1.21"
        kotlin("multiplatform") version "2.1.21"
        kotlin("plugin.serialization") version "2.1.21"
        id("org.jetbrains.compose") version "1.11.1"
        id("org.jetbrains.kotlin.plugin.compose") version "2.1.21"
        id("org.jetbrains.kotlin.plugin.parcelize") version "2.1.21"
        id("com.google.devtools.ksp") version "2.1.21-2.0.1"
        id("androidx.room") version "2.8.4"
        id("com.android.application") version "8.13.2"
        id("com.android.library") version "8.13.2"
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_PROJECT)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CodeNodeIO-DemoProject"

// Composite build: use local CodeNodeIO fbpDsl until it's published as a Maven artifact.
// The CodeNodeIO repo should be a sibling directory (../CodeNodeIO).
// To switch to a published version later:
//   1. Remove this includeBuild block
//   2. Add a version to the fbpDsl dependency: "io.codenode:fbpDsl:1.0.0"
includeBuild("../CodeNodeIO") {
    dependencySubstitution {
        substitute(module("io.codenode:fbpDsl")).using(project(":fbpDsl"))
        substitute(module("io.codenode:graphEditor")).using(project(":graphEditor"))
        substitute(module("io.codenode:circuitSimulator")).using(project(":circuitSimulator"))
        substitute(module("io.codenode:kotlinCompiler")).using(project(":kotlinCompiler"))
    }
}

// Shared modules
include(":persistence")
include(":nodes")

// Mobile app module
include(":KMPMobileApp")

// Generated modules (from FlowGraph compilation)
include(":StopWatch")
include(":UserProfiles")
include(":GeoLocations")
include(":Addresses")
include(":EdgeArtFilter")
include(":WeatherForecast")
include(":launcher")
