plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "ivy.learn.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm()

    sourceSets.commonMain {
        kotlin.srcDir("build/generated/ksp/main/kotlin")
    }
    sourceSets.jvmTest {
        kotlin.srcDir("build/generated/ksp/test/kotlin")
    }

    sourceSets {
        dependencies {
            ksp(libs.arrow.optics.ksp)
        }

        commonMain.dependencies {
            implementation(libs.bundles.ktor.client.common)
            api(libs.kotlin.serialization)
            api(libs.bundles.arrow)
            api(libs.ivyApps.di)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        jsMain.dependencies {
            implementation(libs.ktor.client.js)
        }
        jvmMain.dependencies {
            implementation(libs.ktor.client.java)
        }
        jvmTest.dependencies {
            implementation(libs.bundles.test)
        }
    }
}