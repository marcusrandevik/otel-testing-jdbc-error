plugins {
    id("java")
    id("application")
}

group = "example.testing"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("example.testing.Main")
}

java {
      toolchain {
          languageVersion.set(JavaLanguageVersion.of(21))
      }
  }

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("io.opentelemetry:opentelemetry-api:1.63.0")
    implementation("org.postgresql:postgresql:42.7.11")
    implementation("io.opentelemetry.instrumentation:opentelemetry-jdbc:2.28.1-alpha")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}