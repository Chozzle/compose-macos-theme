plugins {
    id("com.android.application")
    kotlin("android")
    id("org.jetbrains.compose")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(26)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

//    kotlinOptions {
//        jvmTarget = "1.8"
//        useIR = true
//    }
//
//    buildFeatures {
//        compose = true
//    }

//    composeOptions {
//        kotlinCompilerVersion = "1.4.21"
//        kotlinCompilerExtensionVersion = "1.0.0-alpha08"
//    }
}

dependencies {
    implementation(project(":lib"))
//
//    implementation("androidx.compose.ui:ui:1.0.0-alpha08")
//    // Tooling support (Previews, etc.)
//    implementation("androidx.compose.ui:ui-tooling:1.0.0-alpha08")
//    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
//    implementation("androidx.compose.foundation:foundation:1.0.0-alpha08")
//    // Material Design
//    implementation("androidx.compose.material:material:1.0.0-alpha08")
//
//    implementation("androidx.compose.runtime:runtime:1.0.0-alpha08")
    // implementation("androidx.appcompat:appcompat:1.2.0")
}