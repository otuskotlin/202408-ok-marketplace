plugins {
    kotlin("jvm") version "2.0.20"
}

group = "com.otus.otuskotlin.marketplace"
version = "0.0.1"

repositories {
    mavenCentral()
}

subprojects {
    repositories {
        mavenCentral()
    }
    group = rootProject.group
    version = rootProject.version
}
dependencies {
    implementation(kotlin("stdlib-jdk8"))
}
kotlin {
    jvmToolchain(8)
}