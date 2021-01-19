include(":lib", ":example", ":android")
rootProject.name = "compose-macos-theme"

// Renamed so that maven publishing plugin uses this name for artifact
project(":lib").name = "compose-macos-theme"
