@file:Suppress("SpellCheckingInspection")

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "com.animation.exercise"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.animation.exercise"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers"
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":di"))
    implementation(project(":domain"))
    implementation(project(":studio"))

    // Project
    implementation(libs.androidX.coreKtx)
    implementation(libs.kotlinX.coroutines)
    implementation(libs.kotlinX.datetime)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.bundles.compose.icons)
    implementation(libs.bundles.compose.app)
    implementation(libs.bundles.compose.debug)

    // Hilt
    implementation(libs.bundles.hilt)
    kapt(libs.bundles.hiltKapt)

    // Misc
    implementation(libs.timber)

    // Testing
    testImplementation(libs.bundles.testing)
}