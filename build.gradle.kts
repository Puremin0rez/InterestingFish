import com.github.jengelman.gradle.plugins.shadow.tasks.ConfigureShadowRelocation

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.0"
}

group = "net.shotbow"
version = "1.18"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    compileOnly(group = "org.spigotmc", name = "spigot-api", version = "1.13-R0.1-SNAPSHOT")
    implementation(group = "net.kyori", name = "adventure-platform-bukkit", version = "4.0.1")
    implementation(group = "net.kyori", name = "adventure-text-minimessage", version = "4.1.0-SNAPSHOT")
}

defaultTasks("clean", "build")

tasks {
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
        minimize()
        archiveFileName.set("${project.name}.jar")
    }

    register<ConfigureShadowRelocation>("configureShadowRelocation") {
        target = shadowJar.get()
        prefix = "${project.group}.${project.name.toLowerCase()}.libraries"
    }

    build {
        dependsOn(shadowJar).dependsOn("configureShadowRelocation")
    }
}
