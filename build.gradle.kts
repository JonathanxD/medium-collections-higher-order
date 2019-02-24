group = "com.github.jonathanxd.medium"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.3.21"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}