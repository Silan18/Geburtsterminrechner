plugins {
    kotlin("jvm") version "1.7.10"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-server-netty:2.0.0")
    implementation("io.ktor:ktor-html-builder:2.0.0")
    implementation("ch.qos.logback:logback-classic:1.2.6")
    testImplementation("io.ktor:ktor-server-tests:2.0.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.7.10")
}

application {
    mainClass.set("ApplicationKt")
}
