plugins {
    kotlin("jvm") version "1.9.21"
    java
    idea
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:33.0.0-jre")
    testImplementation(kotlin("test-junit5"))
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed")
    }
}
kotlin {
    jvmToolchain(17)
}
idea {
    group
}