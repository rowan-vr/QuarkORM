plugins {
    java
    kotlin("jvm") version "1.9.21"
    antlr
    idea
}

repositories {
    mavenCentral()
}

dependencies {
    antlr("org.antlr:antlr4:4.13.1")
    implementation(project(":quark-commons"))
    testImplementation(kotlin("test"))
}

tasks.generateGrammarSource {
    maxHeapSize = "128m"
    arguments = arguments + listOf("-package","dev.rvr.quark.language", "-visitor", "-no-listener")
    outputDirectory = file("${layout.projectDirectory}/generated-src/antlr/main/dev/rvr/quark/language")
}

sourceSets["main"].java {
    srcDirs("${layout.projectDirectory}/generated-src/antlr/main")
}
sourceSets["test"].java {
    srcDirs("${layout.projectDirectory}/generated-src/antlr/test")
}
tasks.compileKotlin{
    dependsOn("generateGrammarSource")
}
tasks.compileJava{
    dependsOn("generateGrammarSource")
    sourceSets["main"].java {
        srcDirs("${layout.projectDirectory}/generated-src/antlr/main")
    }
    sourceSets["test"].java {
        srcDirs("${layout.projectDirectory}/generated-src/antlr/test")
    }
}
tasks.compileTestKotlin{
    dependsOn("generateTestGrammarSource")
}
tasks.clean {
    delete("${layout.projectDirectory}/generated-src")
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
    module {
        sourceDirs = sourceDirs + file("${layout.projectDirectory}/generated-src/antlr/main/")
    }
}
tasks.assemble {

}