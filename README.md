# MacOS theme for Compose
Multiplatform MacOS theme written in Compose (Compose for Desktop UI framework + Android Jetpack Compose)

Still in alpha, but it's a start... Feedback and help welcome!

![Desktop Example](screenshots/desktop.png)

![Android Example](screenshots/android.png)

### Gradle
#### Multiplatform
Add the common dependency which will work for both android and desktop.
```kotlin
sourceSets {
    named("commonMain") {
        dependencies {
            implementation("io.github.chozzle:compose-macos-theme:0.2.0")
        }
    }
}
```

Optionally supply specific dependencies:

#### Desktop JVM
Currently it appears that for desktop projects, you must use the kotlin multiplatform plugin
```kotlin 
plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    sourceSets {
        named("jvmMain") {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("io.github.chozzle:compose-macos-theme-desktop:0.2.0")
            }
        }
    }
}
```

#### Android
Do not specify android specifically (it won't work). Gradle will automatically import the android sourceset only.
```kotlin 
dependencies {
    implementation("io.github.chozzle:compose-macos-theme:0.2.0")
}
```

Ensure you have the [required compiler options for compose](https://developer.android.com/jetpack/compose/setup#configure_gradle) generally 
or alternatively you can use Jetbrains' plugin:
```kotlin
plugins {
    id("org.jetbrains.compose")
}
```

You'll need Maven Central as a repository

```kotlin
repositories {
    ...
    mavenCentral()
}
```