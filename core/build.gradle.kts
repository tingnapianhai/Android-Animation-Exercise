plugins {
    id("java-library")
    alias(libs.plugins.kotlinJvm)
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

sourceSets {
    named("test") {
        java.srcDirs("src/test/java") // Or the directory where your tests reside
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(libs.kotlinX.coroutines)
}