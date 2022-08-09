import org.jetbrains.compose.compose

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "io.github.chozzle"
version = "0.1.0"

kotlin {
    android()
    jvm("desktop")

    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(project(":compose-windows-theme"))
                implementation(project(":compose-macos-theme"))
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
            }
        }
        named("desktopMain") {
            dependencies {
                implementation(compose.desktop.common)
            }
        }
        named("androidMain") {
            dependencies {
                kotlin.srcDirs("src/jvmMain/kotlin")
            }
        }
    }
}

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res", "src/commonMain/resources")
        }
    }
}