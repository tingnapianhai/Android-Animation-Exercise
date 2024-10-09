pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            versions()
            libs()
            bundles()
            plugins()
        }
    }
}

fun VersionCatalogBuilder.versions() {
    // Project
    // Last supported agp https://developer.android.com/studio/releases#android_gradle_plugin_and_android_studio_compatibility
    version("agp", "8.4.2")
    version("kotlin", "1.9.0")

    // AndroidX
    version("androidX-coreKtx", "1.12.0")
    version("androidX-navigation", "2.7.6")
    version("androidX-lifecycle", "2.6.1")
    version("androidX-activity", "1.8.1")

    // KotlinX
    version("kotlinX-coroutines", "1.7.3")
    version("kotlinX-datetime", "0.4.0")
    version("kotlinX-serialization", "1.5.1")

    // Compose
    version("compose-BOM", "2023.10.01")
    version("compose-compiler", "1.5.2")

    // Hilt
    version("hilt", "2.47")
    version("hilt-compose", "1.0.0")

    // Test
    version("kotest", "5.5.5")
    version("junit", "4.13.2")
    version("test-coroutines", "1.6.4")
    version("test-mockk", "1.13.5")
}

fun VersionCatalogBuilder.libs() {
    // Project
    library("androidX-coreKtx", "androidx.core", "core-ktx").versionRef("androidX-coreKtx")

    // Android X
    library("androidX-savedState", "androidx.lifecycle", "lifecycle-viewmodel-savedstate")
        .versionRef("androidX-lifecycle")

    // Kotlin-X
    library("kotlinX-coroutines", "org.jetbrains.kotlinx", "kotlinx-coroutines-android")
        .versionRef("kotlinX-coroutines")
    library("kotlinX-serialization", "org.jetbrains.kotlinx", "kotlinx-serialization-json")
        .versionRef("kotlinX-serialization")
    library("kotlinX-datetime", "org.jetbrains.kotlinx", "kotlinx-datetime").versionRef("kotlinX-datetime")

    // Hilt
    library("hilt-android", "com.google.dagger", "hilt-android").versionRef("hilt")
    library("hilt-compose", "androidx.hilt", "hilt-navigation-compose").versionRef("hilt-compose")
    library("hilt-compiler", "com.google.dagger", "hilt-android-compiler").versionRef("hilt")

    // Compose
    library("compose-bom", "androidx.compose", "compose-bom").versionRef("compose-BOM")
    library("compose-runtime", "androidx.compose.runtime", "runtime").withoutVersion()
    library("compose-ui", "androidx.compose.ui", "ui").withoutVersion()
    library("compose-foundation", "androidx.compose.foundation", "foundation").withoutVersion()
    library("compose-tooling", "androidx.compose.ui", "ui-tooling").withoutVersion()
    library("compose-tooling-preview", "androidx.compose.ui", "ui-tooling-preview").withoutVersion()
    library("compose-icons", "androidx.compose.material", "material-icons-core").withoutVersion()
    library("compose-iconsExtended", "androidx.compose.material", "material-icons-extended").withoutVersion()
    library("compose-material3", "androidx.compose.material3", "material3").withoutVersion()
    // -- Non BOM Versions
    library("compose-activity", "androidx.activity", "activity-compose").versionRef("androidX-activity")
    library("compose-navigation", "androidx.navigation", "navigation-compose").versionRef("androidX-navigation")
    library("compose-lifecycle", "androidx.lifecycle", "lifecycle-runtime-compose").versionRef("androidX-lifecycle")

    // Misc
    library("timber", "com.jakewharton.timber:timber:5.0.1")

    // Test
    library("test-junit", "junit", "junit").versionRef("junit")
    library("test-kotest-junit", "io.kotest", "kotest-runner-junit5").versionRef("kotest")
    library("test-kotest-assertions", "io.kotest", "kotest-assertions-core").versionRef("kotest")
    library("test-kotest-property", "io.kotest", "kotest-property").versionRef("kotest")
    library("test-coroutines", "org.jetbrains.kotlinx", "kotlinx-coroutines-test").versionRef("test-coroutines")
    library("test-mockk", "io.mockk", "mockk").versionRef("test-mockk")

}

fun VersionCatalogBuilder.bundles() {
    bundle(
        "compose",
        listOf(
            "compose-bom",
            "compose-foundation",
            "compose-ui",
            "compose-runtime",
            "compose-material3",
            "compose-lifecycle"
        )
    )
    bundle("compose-debug", listOf("compose-tooling", "compose-tooling-preview"))
    bundle("compose-app", listOf("compose-activity", "compose-navigation"))
    bundle("compose-icons", listOf("compose-icons", "compose-iconsExtended"))

    bundle("hilt", listOf("hilt-android", "hilt-compose"))
    bundle("hiltKapt", listOf("hilt-compiler"))


    bundle(
        "testing",
        listOf(
            "test-junit",
            "test-coroutines",
            "test-mockk",
            "test-kotest-junit",
            "test-kotest-assertions",
        )
    )
}

fun VersionCatalogBuilder.plugins() {
    plugin("androidApplication", "com.android.application").versionRef("agp")
    plugin("androidLibrary", "com.android.library").versionRef("agp")
    plugin("jetbrainsKotlinAndroid", "org.jetbrains.kotlin.android").versionRef("kotlin")
    plugin("kotlinJvm", "org.jetbrains.kotlin.jvm").versionRef("kotlin")
    plugin("kotlinxSerialization", "org.jetbrains.kotlin.plugin.serialization").versionRef("kotlin")
    plugin("hiltAndroid", "com.google.dagger.hilt.android").version("2.47")
}

rootProject.name = "Animation Exercise"
include(":app")
include(":studio")
include(":domain")
include(":data")
include(":di")
include(":core")
