buildscript {
    repositories {
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        jcenter()
        mavenCentral()
    }

    dependencies {
        // __LATEST_COMPOSE_RELEASE_VERSION__
        classpath("org.jetbrains.compose:compose-gradle-plugin:1.2.0-alpha01-dev725")
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath(kotlin("gradle-plugin", version = "1.6.21"))
    }
}

group = "io.github.chozzle"

allprojects {
    repositories {
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        jcenter()
    }
}
