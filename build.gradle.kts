buildscript {
    repositories {
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        jcenter()
        mavenCentral()
    }

    dependencies {
        // __LATEST_COMPOSE_RELEASE_VERSION__
        classpath("org.jetbrains.compose:compose-gradle-plugin:1.0.0-beta1")
        classpath("com.android.tools.build:gradle:4.2.0")
        classpath(kotlin("gradle-plugin", version = "1.5.31"))
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
