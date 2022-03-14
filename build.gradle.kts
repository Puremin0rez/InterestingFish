import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.1"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "net.shotbow"
version = "1.18"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    compileOnly(group = "org.spigotmc", name = "spigot-api", version = "1.17.1-R0.1-SNAPSHOT")
    implementation(group = "net.kyori", name = "adventure-platform-bukkit", version = "4.1.0")
    implementation(group = "net.kyori", name = "adventure-text-minimessage", version = "4.1.0")
    implementation(group = "com.uchuhimo", name = "konf", version = "1.1.2")
    runtimeOnly(kotlin("reflect"))
}

defaultTasks("clean", "build")

tasks {
    wrapper {
        gradleVersion = "7.4.1"
        distributionType = Wrapper.DistributionType.ALL
    }

    processResources {
        val placeholders = mapOf(
            "name" to project.name,
            "group" to project.group,
            "version" to project.version
        )
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

        relocate("net.kyori", "${project.group}.${project.name.toLowerCase()}.libraries.net.kyori")

        minimize()
        archiveFileName.set("${project.name}.jar")
    }

    build {
        dependsOn(shadowJar)
    }

    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}
