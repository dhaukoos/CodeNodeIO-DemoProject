plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

kotlin {
    jvm()

    sourceSets {
        val jvmMain by getting {
            dependencies {
                // graphEditor and tool dependencies (via composite build)
                implementation("io.codenode:graphEditor")
                implementation("io.codenode:circuitSimulator")
                implementation("io.codenode:kotlinCompiler")

                // All project modules
                implementation(project(":StopWatch"))
                implementation(project(":UserProfiles"))
                implementation(project(":GeoLocations"))
                implementation(project(":Addresses"))
                implementation(project(":EdgeArtFilter"))
                implementation(project(":WeatherForecast"))
                implementation(project(":persistence"))
                implementation(project(":nodes"))

                // Compose Desktop runtime
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "io.codenode.launcher.MainKt"
        nativeDistributions {
            packageName = "CodeNodeIO Graph Editor"
            packageVersion = "1.0.0"
        }
    }
}
