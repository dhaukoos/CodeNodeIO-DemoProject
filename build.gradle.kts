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

    // Build classpath: tool JARs + project module JARs + all transitive dependencies
    val toolBuildDir = gradle.includedBuild("CodeNodeIO").projectDir

    classpath = files(
        // Tool module JARs (via composite build)
        fileTree(toolBuildDir.resolve("graphEditor/build/libs")) { include("*.jar") },
        fileTree(toolBuildDir.resolve("fbpDsl/build/libs")) { include("*.jar") },
        fileTree(toolBuildDir.resolve("circuitSimulator/build/libs")) { include("*.jar") },
        fileTree(toolBuildDir.resolve("kotlinCompiler/build/libs")) { include("*.jar") },
        // Project module JARs
        subprojects.filter { it.name != "KMPMobileApp" }.map { subproject ->
            fileTree(subproject.layout.buildDirectory.dir("libs")) { include("*.jar") }
        }
    )

    // Pass project directory so the graphEditor discovers modules here
    environment("CODENODE_PROJECT_DIR", projectDir.absolutePath)

    // Ensure everything is compiled before running
    dependsOn(
        subprojects.filter { it.name != "KMPMobileApp" }.map { ":${it.name}:jvmJar" }
    )
    dependsOn(gradle.includedBuild("CodeNodeIO").task(":graphEditor:jvmJar"))
    dependsOn(gradle.includedBuild("CodeNodeIO").task(":fbpDsl:jvmJar"))
    dependsOn(gradle.includedBuild("CodeNodeIO").task(":circuitSimulator:jvmJar"))
    dependsOn(gradle.includedBuild("CodeNodeIO").task(":kotlinCompiler:jvmJar"))
}
