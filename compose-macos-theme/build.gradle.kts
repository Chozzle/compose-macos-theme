import org.jetbrains.compose.compose
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("maven-publish")
    id("signing")
}

version = "0.4.0"
group = "io.github.chozzle"

kotlin {
    android()
    jvm("desktop")

    sourceSets {
        named("commonMain") {
            dependencies {
                implementation(kotlin("stdlib-common"))
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
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
        versionCode = 1
        versionName = "1.0"
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

kotlin {
    android {
        publishLibraryVariants("release", "debug")
    }
}

// Signing environment variables //
extra["signing.keyId"] = ""
extra["signing.password"] = ""
extra["signing.secretKeyRingFile"] = ""
extra["ossrhUsername"] = ""
extra["ossrhPassword"] = ""

val secretPropsFile = project.rootProject.file("local.properties")
if (secretPropsFile.exists()) {
    println("Found secret props file, loading props")
    val p = Properties()
    p.load(FileInputStream(secretPropsFile))
    p.forEach { name, value ->
        extra[name as String] = value
    }
} else {
    // This is to facilitate CD in the future
    println("No props file, loading env vars")
    extra["signing.keyId"] = System.getenv("SIGNING_KEY_ID")
    extra["signing.password"] = System.getenv("SIGNING_PASSWORD")
    extra["signing.secretKeyRingFile"] = System.getenv("SIGNING_SECRET_KEY_RING_FILE")
    extra["ossrhUsername"] = System.getenv("OSSRH_USERNAME")
    extra["ossrhPassword"] = System.getenv("OSSRH_PASSWORD")
}

// Create empty jar for javadoc classifier to satisfy maven requirements
tasks.register<Jar>("stubJavadoc") {
    archiveClassifier.set("javadoc")
}

signing {
    sign(publishing.publications)
}

publishing {
    publications.withType<MavenPublication>().configureEach {
        pom {
            name.set("MacOS Theme for Compose")
            description.set("A collection of components that follow MacOS Theme, written in Compose UI")
            url.set("https://github.com/chozzle/compose-macos-theme")

            licenses {
                license {
                    name.set("The Apache Software License, Version 2.0")
                    url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    distribution.set("repo")
                }
            }
            developers {
                developer {
                    id.set("Chozzle")
                    name.set("Carson Holzheimer")
                }
            }
            scm {
                url.set("https://github.com/chozzle/compose-macos-theme")
            }
        }

        // Patch publications with fake javadoc
        artifact(tasks.findByName("stubJavadoc"))
    }
    // Patch publications with fake javadoc
    kotlin.targets.forEach { target ->
        val targetPublication = publications.findByName(target.name)
        if (targetPublication != null) {
            targetPublication.name
        }
    }
    repositories {
        maven {
            name = "mavencentral"

            val releasesRepoUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2"
            val snapshotsRepoUrl = "https://oss.sonatype.org/content/repositories/snapshots/"

            // You only need this if you want to publish snapshots, otherwise just set the URL
            // to the release repo directly
            url = if (version.toString().endsWith("SNAPSHOT")) {
                uri(snapshotsRepoUrl)
            } else {
                uri(releasesRepoUrl)
            }

            authentication {
                credentials {
                    username = project.extra["ossrhUsername"] as String
                    password = project.extra["ossrhPassword"] as String
                }
            }
        }
    }
}