plugins {
    id("ivy.shared-module")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.bundles.ktor.client.common)
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
    }
}
