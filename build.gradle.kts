import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion

plugins {
    kotlin("jvm") version "2.4.21"
    id("org.jlleitschuh.gradle.ktlint") version "14.2.0"
    id("com.gradleup.shadow") version "9.6.1"
}

group = "net.shotbow"
version = "26.3"

kotlin {
    jvmToolchain(25)
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:26.3-R0.1-SNAPSHOT")
    implementation("net.kyori:adventure-text-minimessage:5.2.0")
    implementation("net.kyori:adventure-text-serializer-legacy:5.2.0")
}

ktlint {
    version.set("1.8.0")
}

defaultTasks("clean", "build")

tasks {
    wrapper {
        gradleVersion = "9.8.1"
        distributionType = Wrapper.DistributionType.ALL
    }

    processResources {
        val placeholders = mapOf(
            "name" to project.name,
            "group" to project.group,
            "version" to project.version,
            "kotlinVersion" to getKotlinPluginVersion()
        )
        inputs.properties(placeholders)
        filesMatching("plugin.yml") {
            expand(placeholders)
        }
    }

    jar {
        archiveFileName.set("${project.name}-noshade.jar")
    }

    shadowJar {
        dependencies {
            include(dependency("net.kyori:.*"))
        }

        filesMatching("META-INF/*.kotlin_module") {
            duplicatesStrategy = DuplicatesStrategy.INCLUDE
        }

        relocate("net.kyori", "${project.group}.${project.name.lowercase()}.libraries.net.kyori")

        minimize()
        archiveFileName.set("${project.name}.jar")
    }

    build {
        dependsOn(shadowJar)
    }
}
