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
        id("org.jetbrains.compose") version "1.10.0"
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

// =============================================================================
// Composite build: local CodeNodeIO tool repository
// =============================================================================
// Currently uses a local checkout of the CodeNodeIO tool repository as a
// sibling directory (../CodeNodeIO). This provides fbpDsl, preview-api,
// graphEditor, and other tool modules at compile time.
//
// To switch to published Maven artifacts (when fbpDsl and preview-api are released):
//   1. Remove the entire includeBuild block below
//   2. Add versions to module dependencies in build files:
//        "io.codenode:fbpDsl"       → "io.codenode:fbpDsl:1.0.0"
//        "io.codenode:preview-api"  → "io.codenode:preview-api:1.0.0"
//   3. The graphEditor/circuitSimulator substitutions can be
//      removed entirely (only needed for runGraphEditor task and IDE integration)
// =============================================================================
val codeNodeToolRepo = file("../CodeNodeIO")
if (codeNodeToolRepo.isDirectory) {
    includeBuild(codeNodeToolRepo) {
        dependencySubstitution {
            substitute(module("io.codenode:fbpDsl")).using(project(":fbpDsl"))
            substitute(module("io.codenode:preview-api")).using(project(":preview-api"))
            substitute(module("io.codenode:graphEditor")).using(project(":graphEditor"))
            substitute(module("io.codenode:circuitSimulator")).using(project(":circuitSimulator"))
            substitute(module("io.codenode:flowGraph-types")).using(project(":flowGraph-types"))
            substitute(module("io.codenode:flowGraph-persist")).using(project(":flowGraph-persist"))
            substitute(module("io.codenode:flowGraph-inspect")).using(project(":flowGraph-inspect"))
            substitute(module("io.codenode:flowGraph-execute")).using(project(":flowGraph-execute"))
            substitute(module("io.codenode:flowGraph-generate")).using(project(":flowGraph-generate"))
            substitute(module("io.codenode:flowGraph-compose")).using(project(":flowGraph-compose"))
        }
    }
} else {
    logger.warn("CodeNodeIO tool repository not found at ../CodeNodeIO — composite build disabled. " +
        "Published Maven artifacts required for fbpDsl and preview-api.")
}

// Shared modules
include(":iptypes")
include(":persistence")
include(":nodes")

// Mobile app module
include(":KMPMobileApp")

// Generated modules (from FlowGraph compilation)
include(":StopWatch")
include(":UserProfiles")
include(":Addresses")
include(":EdgeArtFilter")
include(":WeatherForecast")
