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
}

dependencies {
    implementation(project(":compose-macos-theme"))

    implementation("androidx.appcompat:appcompat:1.3.0-beta01")
    // See https://stackoverflow.com/a/66146595/4672107
    implementation("androidx.activity:activity-compose:1.3.0-alpha02") {
        exclude(group = "androidx.compose.animation")
        exclude(group = "androidx.compose.foundation")
        exclude(group = "androidx.compose.material")
        exclude(group = "androidx.compose.runtime")
        exclude(group = "androidx.compose.ui")
    }

    implementation(compose.runtime)
    implementation(compose.foundation)
    implementation(compose.material)
}