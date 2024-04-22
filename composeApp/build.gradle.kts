plugins {
    id("ivy.compose-app")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared)
        }
    }
}

android {
    namespace = "ivy.learn"
}