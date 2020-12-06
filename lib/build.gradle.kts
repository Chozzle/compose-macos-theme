import org.jetbrains.compose.compose

plugins {
    //id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.compose") version "0.2.0-build132"
}

group = "me.carsonholzheimer"
version = "1.0"

repositories {
    google()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
}

kotlin {
    jvm("desktop")
    //android()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material) // Maybe not needed?
                implementation(compose.materialIconsExtended) // Probably not needed
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
            }
        }
//        val androidMain by getting {
//            dependencies {
//                implementation("com.google.android.material:material:1.2.1")
//            }
//        }
    }
}
//
//android {
//    compileSdkVersion(30)
//
//    defaultConfig {
//        minSdkVersion(21)
//        targetSdkVersion(30)
//        versionCode = 1
//        versionName = "1.0"
//    }
//
//    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
//    }
//
//    sourceSets {
//        named("main") {
//            manifest.srcFile("src/androidMain/AndroidManifest.xml")
//            res.srcDirs("src/androidMain/res", "src/commonMain/resources")
//        }
//    }
//}