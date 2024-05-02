plugins {
    id("ivy.shared-module")
    id("ivy.serialization")
    id("ivy.ktor-client")
}

android {
    namespace = "ivy.learn.shared"
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.arrow.core)
        }
    }
}