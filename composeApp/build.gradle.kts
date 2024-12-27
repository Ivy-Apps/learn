import com.google.devtools.ksp.gradle.KspTask

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.composeMultiplatform)
  alias(libs.plugins.composeCompiler)
  alias(libs.plugins.ksp)
  idea
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

  jvm("desktop")

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach { iosTarget ->
    iosTarget.binaries.framework {
      baseName = "ComposeApp"
      isStatic = true
    }
  }

  sourceSets {
    commonMain {
      kotlin.srcDirs("build/generated/ksp/commonMain/kotlin")
    }
    commonTest {
      kotlin.srcDir("build/generated/ksp/test/kotlin")
    }
    dependencies {
      ksp(libs.arrow.optics.ksp)
    }

    val desktopMain by getting

    androidMain.dependencies {
      implementation(compose.preview)
      implementation(libs.androidx.activity.compose)
    }
    commonMain.dependencies {
      implementation(projects.shared)
      implementation(compose.runtime)
      implementation(compose.foundation)
      implementation(compose.material)
      implementation(compose.ui)
      implementation(compose.components.resources)
      implementation(compose.components.uiToolingPreview)
      implementation(libs.kotlin.immutableCollections)
      implementation(libs.thirdparty.lottieMultiplatform)
      implementation(libs.thirdparty.kamel)
      implementation(libs.bundles.arrow)
    }
    commonTest.dependencies {
      implementation(libs.bundles.test.kmp)
    }
    desktopMain.dependencies {
      implementation(compose.desktop.currentOs)
    }
  }
}

android {
  namespace = "ivy.learn"
  compileSdk = libs.versions.android.compileSdk.get().toInt()

  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  sourceSets["main"].res.srcDirs("src/androidMain/res")
  sourceSets["main"].resources.srcDirs("src/commonMain/resources")

  defaultConfig {
    applicationId = "ivy.learn"
    minSdk = libs.versions.android.minSdk.get().toInt()
    targetSdk = libs.versions.android.targetSdk.get().toInt()
    versionCode = 1
    versionName = "1.0"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
}

compose.experimental {
  web.application {}
}

// Configure KSP to output to the commonMain directory
tasks.withType<KspTask> {
  doLast {
    fixKspConflicts()
  }
}

fun fixKspConflicts() {
  fun fixTarget(target: String) {
    copy {
      from("build/generated/ksp/$target/${target}Main/kotlin")
      into("build/generated/ksp/commonMain/kotlin")
      include("**/*.kt")
    }
    delete("build/generated/ksp/$target/${target}Main/kotlin")
  }
  fixTarget("js")
  fixTarget("desktop")
  fixTarget("android")
  fixTarget("ios")
  fixTarget("native")
}