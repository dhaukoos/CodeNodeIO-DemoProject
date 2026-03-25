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
├── CodeNodeIO/              # Tool repository (contains fbpDsl)
└── CodeNodeIO-DemoProject/  # This repository
```

### Build

```bash
git clone git@github.com:dhaukoos/CodeNodeIO.git
git clone git@github.com:dhaukoos/CodeNodeIO-DemoProject.git
cd CodeNodeIO-DemoProject
./gradlew build
```

The build uses a Gradle [composite build](https://docs.gradle.org/current/userguide/composite_builds.html) to include `fbpDsl` from the sibling CodeNodeIO directory.

### Switching to Published fbpDsl

When `fbpDsl` is published as a Maven artifact, update `settings.gradle.kts`:

1. Remove the `includeBuild("../CodeNodeIO")` block
2. Add a version to fbpDsl dependencies in module `build.gradle.kts` files:
   ```kotlin
   implementation("io.codenode:fbpDsl:1.0.0")
   ```

## License

Apache 2.0
