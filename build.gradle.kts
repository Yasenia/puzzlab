plugins {
    java
    checkstyle
}

group = "io.github.yasenia"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.hamcrest:hamcrest:2.2")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

checkstyle {
    maxWarnings = 0
    toolVersion = "10.12.1"
}

tasks {
    test {
        useJUnitPlatform()
    }
}
