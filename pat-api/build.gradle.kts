plugins {
    id("org.jetbrains.dokka") version "1.5.0"
    `maven-publish`
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.21")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("io.github.monun:tap-api:4.3.0")
}

tasks {
    create<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }

    create<Jar>("dokkaJar") {
        archiveClassifier.set("javadoc")
        dependsOn("dokkaHtml")

        from(project.rootDir.resolve("docs")) {
            include("**")
        }
    }

    dokkaHtml.configure {
        outputDirectory.set(project.rootDir.resolve("docs"))
    }
}

publishing {
    publications {
        create<MavenPublication>("pat-api") {
            groupId = project.properties["group"] as String
            version = project.properties["version"] as String
            artifactId = "pat-api"

            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["dokkaJar"])
        }
    }
}