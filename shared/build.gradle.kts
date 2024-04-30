plugins {
    id("ivy.shared-module")
    id("ivy.serialization")
    id("ivy.ktor-client")
    `maven-publish`
}

group = "ivy.learn.shared"
version = "1.0.4"

publishing {
    publications {
        create<MavenPublication>("maven") {
            // This line specifies that the output of the 'kotlin' component should be published
            from(components["kotlin"])
        }
    }
}

android {
    namespace = "ivy.learn.shared"
}
