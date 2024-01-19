plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "quark"
include("quark-language")
include("quark-commons")
include("quark-databases")
