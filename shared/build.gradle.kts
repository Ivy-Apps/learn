plugins {
    id("ivy.shared-module")
    id("ivy.serialization")
    id("ivy.ktor-client")
    `maven-publish`
}

group = "ivy.learn.shared"
version = "1.0.6"


publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])
        }
    }
}

android {
    namespace = "ivy.learn.shared"
}
