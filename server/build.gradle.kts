plugins {
    id("org.jetbrains.kotlin.jvm")
    id("io.ktor.plugin")
    application
}

group = "ivy.learn"
version = "1.0.0"
application {
    mainClass.set("ivy.learn.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

tasks {
    create("stage").dependsOn("installDist")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.arrow.core)
    implementation(libs.bundles.jetbrains.exposed)
    implementation(libs.postgresql.driver)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)
    testImplementation(libs.bundles.test)
}