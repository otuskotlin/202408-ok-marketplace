plugins {
    application
    id("build-jvm")
    alias(libs.plugins.muschko.java)
}

application {
    mainClass.set("ru.otus.otuskotlin.marketplace.app.kafka.MainKt")
}

docker {
    javaApplication {
        baseImage.set("openjdk:${libs.versions.jvm.compiler.get()}-slim")
    }
}

dependencies {
    implementation(libs.kafka.client)
    implementation(libs.coroutines.core)
    implementation(libs.kotlinx.atomicfu)

    implementation(libs.mkpl.logs.logback)

    implementation(project(":ok-marketplace-app-common"))

    // transport models
    implementation(project(":ok-marketplace-common"))
    implementation(project(":ok-marketplace-api-v1-jackson"))
    implementation(project(":ok-marketplace-api-v1-mappers"))
    implementation(project(":ok-marketplace-api-v2-kmp"))
    // logic
    implementation(project(":ok-marketplace-biz"))

    testImplementation(kotlin("test-junit"))
}
