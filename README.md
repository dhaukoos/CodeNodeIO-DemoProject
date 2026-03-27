# CodeNodeIO-DemoProject

Demonstration project created with [CodeNodeIO](https://github.com/dhaukoos/CodeNodeIO) — a visual flow-based programming editor for Kotlin Multiplatform.

## Modules

| Module | Description |
|--------|-------------|
| **StopWatch** | Timer demo with elapsed seconds/minutes display |
| **UserProfiles** | CRUD user profiles with Room persistence |
| **GeoLocations** | CRUD geo locations with Room persistence |
| **Addresses** | CRUD addresses with Room persistence |
| **EdgeArtFilter** | Image processing pipeline (grayscale, sepia, edge detection) |
| **WeatherForecast** | Weather API demo with Open-Meteo integration |
| **KMPMobileApp** | Kotlin Multiplatform mobile app (Android/iOS) |
| **persistence** | Shared Room database module |
| **nodes** | Project-level shared node definitions |

## Setup

### Prerequisites

- JDK 17+
- Android SDK (for KMPMobileApp)
- The [CodeNodeIO](https://github.com/dhaukoos/CodeNodeIO) repository cloned as a sibling directory

### Directory Structure

```
parent-directory/
├── CodeNodeIO/              # Tool repository (provides fbpDsl, preview-api, graphEditor)
└── CodeNodeIO-DemoProject/  # This repository
```

### Build

```bash
git clone git@github.com:dhaukoos/CodeNodeIO.git
git clone git@github.com:dhaukoos/CodeNodeIO-DemoProject.git
cd CodeNodeIO-DemoProject
./gradlew jvmJar
```

The build uses a Gradle [composite build](https://docs.gradle.org/current/userguide/composite_builds.html) to include `fbpDsl` and `preview-api` from the sibling CodeNodeIO directory. If the CodeNodeIO directory is not found, the build will warn and require published Maven artifacts instead.

### Running the graphEditor

To launch the graphEditor with all project modules available for runtime preview:

```bash
# From CodeNodeIO-DemoProject:
./gradlew runGraphEditor

# Or from CodeNodeIO (with CODENODE_PROJECT_DIR set):
# 1. Build the project first:
cd CodeNodeIO-DemoProject && ./gradlew jvmJar writeRuntimeClasspath --rerun-tasks
# 2. Run from CodeNodeIO:
cd ../CodeNodeIO && ./gradlew :graphEditor:run
```

### Dependency Strategy

This project depends on two libraries from CodeNodeIO:

| Library | Purpose | Dependency |
|---------|---------|------------|
| **fbpDsl** | Flow-based programming DSL (runtime, nodes, channels) | `implementation("io.codenode:fbpDsl")` |
| **preview-api** | PreviewRegistry for composable preview dispatch | `implementation("io.codenode:preview-api")` |

**Current**: Resolved via composite build from the local `../CodeNodeIO` directory.

**After publication**: Replace the composite build with versioned Maven artifacts:

1. Remove the `includeBuild("../CodeNodeIO")` block from `settings.gradle.kts`
2. Add versions to dependencies in module `build.gradle.kts` files:
   ```kotlin
   implementation("io.codenode:fbpDsl:1.0.0")
   implementation("io.codenode:preview-api:1.0.0")
   ```

### Version Catalog

Dependency versions are centralized in `gradle/libs.versions.toml`. Key versions:

| Component | Version |
|-----------|---------|
| Kotlin | 2.1.21 |
| Compose | 1.7.3 (libraries), 1.10.0 (plugin) |
| Room | 2.8.4 |
| Koin | 4.0.0 |
| Ktor | 3.1.1 |
| Coroutines | 1.8.0 |

## License

Apache 2.0
