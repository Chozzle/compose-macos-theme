buildscript {
    repositories {
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        jcenter()
        mavenCentral()
    }

    dependencies {
        // __LATEST_COMPOSE_RELEASE_VERSION__
        classpath("org.jetbrains.compose:compose-gradle-plugin:0.3.0-build152")
        classpath("com.android.tools.build:gradle:4.0.1")
        classpath(kotlin("gradle-plugin", version = "1.4.30"))
    }
}

allprojects {
    repositories {
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        jcenter()
    }
}
