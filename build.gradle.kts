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

// Configuration to collect all runtime JARs for the graphEditor launch
val graphEditorRuntime by configurations.creating {
    isCanBeResolved = true
    isCanBeConsumed = false
}

dependencies {
    // graphEditor and all tool dependencies (via composite build substitution)
    graphEditorRuntime("io.codenode:graphEditor") {
        attributes {
            attribute(Attribute.of("org.jetbrains.kotlin.platform.type", String::class.java), "jvm")
        }
    }
    // All project modules as runtime dependencies
    graphEditorRuntime(project(":StopWatch")) {
        attributes {
            attribute(Attribute.of("org.jetbrains.kotlin.platform.type", String::class.java), "jvm")
        }
    }
    graphEditorRuntime(project(":UserProfiles")) {
        attributes {
            attribute(Attribute.of("org.jetbrains.kotlin.platform.type", String::class.java), "jvm")
        }
    }
    graphEditorRuntime(project(":GeoLocations")) {
        attributes {
            attribute(Attribute.of("org.jetbrains.kotlin.platform.type", String::class.java), "jvm")
        }
    }
    graphEditorRuntime(project(":Addresses")) {
        attributes {
            attribute(Attribute.of("org.jetbrains.kotlin.platform.type", String::class.java), "jvm")
        }
    }
    graphEditorRuntime(project(":EdgeArtFilter")) {
        attributes {
            attribute(Attribute.of("org.jetbrains.kotlin.platform.type", String::class.java), "jvm")
        }
    }
    graphEditorRuntime(project(":WeatherForecast")) {
        attributes {
            attribute(Attribute.of("org.jetbrains.kotlin.platform.type", String::class.java), "jvm")
        }
    }
    graphEditorRuntime(project(":persistence")) {
        attributes {
            attribute(Attribute.of("org.jetbrains.kotlin.platform.type", String::class.java), "jvm")
        }
    }
    graphEditorRuntime(project(":nodes")) {
        attributes {
            attribute(Attribute.of("org.jetbrains.kotlin.platform.type", String::class.java), "jvm")
        }
    }
}

/**
 * Launches the CodeNodeIO graphEditor with all project modules on the classpath.
 *
 * This is the recommended way to run the graphEditor against this project.
 * The graphEditor discovers modules from the project directory and can execute
 * their runtime pipelines because the compiled classes are on the classpath.
 *
 * Usage: ./gradlew runGraphEditor
 */
tasks.register<JavaExec>("runGraphEditor") {
    description = "Launch CodeNodeIO graphEditor with all project modules"
    group = "application"

    mainClass.set("io.codenode.grapheditor.MainKt")

    // Use Gradle's fully-resolved classpath (includes all transitive dependencies)
    classpath = graphEditorRuntime

    // Set working directory to the project root (important for JFileChooser on macOS)
    workingDir = projectDir

    // Pass project directory so the graphEditor discovers modules here
    environment("CODENODE_PROJECT_DIR", projectDir.absolutePath)

    // macOS: required for proper AWT/Swing behavior (file dialogs, menus)
    if (System.getProperty("os.name").lowercase().contains("mac")) {
        jvmArgs("-Dapple.awt.application.appearance=system")
        jvmArgs("-Dapple.laf.useScreenMenuBar=true")
    }
}
