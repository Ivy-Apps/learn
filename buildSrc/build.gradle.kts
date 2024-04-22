plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    maven(url = "https://jitpack.io")
    gradlePluginPortal()
}

dependencies {
    implementation(libs.plugin.androidApplication)
    implementation(libs.plugin.androidLibrary)
    implementation(libs.plugin.jetbrainsCompose)
    implementation(libs.plugin.kotlinJvm)
    implementation(libs.plugin.kotlinMultiplatform)
    implementation(libs.plugin.kotlinSerialization)
    implementation(libs.plugin.ktor)

    // Make version catalog available in precompiled scripts
    // https://github.com/gradle/gradle/issues/15383#issuecomment-1567461389
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}