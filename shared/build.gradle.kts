plugins {
    id("ivy.shared-module")
    id("ivy.serialization")
    id("ivy.ktor-client")
    id("com.google.devtools.ksp")
}

android {
    namespace = "ivy.learn.shared"
}

kotlin {
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
            implementation(libs.bundles.arrow)
        }
    }
}