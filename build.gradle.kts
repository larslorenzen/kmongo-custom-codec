val junitVersion: String by project
val kmongoVersion: String by project

plugins {
    kotlin("jvm") version "1.3.61"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.litote.kmongo:kmongo-coroutine-native:$kmongoVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "12"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "12"
    }
}
