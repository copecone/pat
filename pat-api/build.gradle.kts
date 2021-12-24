import java.util.*

plugins {
    id("org.jetbrains.dokka") version "1.5.0"
    `maven-publish`
}

val secretPropsFile = project.rootProject.file("local.properties")
if (secretPropsFile.exists()) {
    secretPropsFile.reader().use {
        Properties().apply {
            load(it)
        }
    }.onEach { (name, value) ->
        ext[name.toString()] = value
    }
} else {
    ext["githubUsername"] = System.getenv("GITHUB_ACTOR")
    ext["githubToken"] = System.getenv("GITHUB_TOKEN")
}

// ext["githubUsername"] = null
// ext["githubToken"] = null

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

    test {
        outputs.upToDateWhen { false }
    }

    publish {
        outputs.upToDateWhen { false }
    }
}

fun getExtraString(name: String) = ext[name]?.toString()

publishing {
    repositories {
        maven {
            name = "GithubPackages"
            url = uri("https://maven.pkg.github.com/copecone/pat")
            credentials.runCatching {
                username = getExtraString("githubUsername")
                password = getExtraString("githubToken")
            }.onFailure {
                logger.warn("Failed to load github packages credentials, Check the environment variables")
            }
        }
    }

    publications {
        register<MavenPublication>(project.name) {
            artifactId = project.name
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["dokkaJar"])
        }
    }
}