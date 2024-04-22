import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    id("ivy.shared-module")
    id("ivy.serialization")
    id("ivy.ktor-client")
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                    }
                }
            }
        }
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

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.serialization)
            implementation(libs.bundles.ktor.client.common)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        named("wasmJsMain").dependencies {
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

android {
    namespace = "ivy.learn.shared"
}
