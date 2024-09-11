plugins {
    kotlin("jvm") version "2.0.0"
}

group = "org.krcoding"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-websockets:2.3.1")

    implementation("org.java-websocket:Java-WebSocket:1.5.2")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.8.10")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<JavaExec>("runServer") {
    group = "application"
    description = "Run the WebSocket server"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set("org.krcoding.MainKt")
}

kotlin {
    jvmToolchain(21)
}
