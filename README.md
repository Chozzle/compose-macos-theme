# MacOS theme for Compose
Multiplatform MacOS theme written in Compose (Compose for Desktop UI framework + Android Jetpack Compose)

Still in alpha, but it's a start... Feedback and help welcome!

![Desktop Example](screenshots/desktop.png)

![Android Example](screenshots/android.png)

### Gradle

Add the common dependency which will work for both android and desktop.
```kotlin
sourceSets {
    named("commonMain") {
        dependencies {
            implementation("io.github.chozzle:compose-macos-theme:0.1.0")
        }
    }
}
```

Optionally supply specific dependencies:

#### Desktop JVM
`implementation("io.github.chozzle:compose-macos-theme-desktop:0.1.0")`

#### Android (currently not working sorry, looking into it)
`implementation("io.github.chozzle:compose-macos-theme-android:0.1.0")`

Ensure you have Maven Central as a repository

```kotlin
repositories {
    ...
    mavenCentral()
}
```