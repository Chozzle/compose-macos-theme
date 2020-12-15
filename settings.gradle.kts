pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
        jcenter() // jcenter last due to some reports of fake copied repos
    }
//    resolutionStrategy {
//        eachPlugin {
//            if (requested.id.id == "kotlin-multiplatform") {
//                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
//            }
//            if (requested.id.namespace == "com.android" || requested.id.name == "kotlin-android-extensions") {
//                useModule("com.android.tools.build:gradle:4.0.1")
//            }
//        }
//    }
}
rootProject.name = "compose-macos-theme"
include(":lib", ":example", ":android")
