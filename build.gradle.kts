import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion

plugins {
    kotlin("jvm")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        maven("https://papermc.io/repo/repository/maven-public/")
        maven("https://jitpack.io")
    }

    dependencies {
        compileOnly("io.papermc.paper:paper-api:1.18-R0.1-SNAPSHOT")

        implementation(kotlin("stdlib"))

        testImplementation("io.papermc.paper:paper-api:1.18-R0.1-SNAPSHOT")
        // testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
        // testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
        // testImplementation("junit:junit:4.12")
        testImplementation("org.jetbrains.kotlin:kotlin-test:${getKotlinPluginVersion()}")
        testImplementation("org.mockito:mockito-core:3.6.28")
    }

    tasks {
        test {
            useJUnitPlatform()
        }
    }
}