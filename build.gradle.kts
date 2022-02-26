import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("org.jmailen.kotlinter") version "3.8.0"
    id("com.github.ben-manes.versions") version "0.42.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1" apply false
    id("net.minecrell.plugin-yml.bungee") version "0.5.1" apply false
    id("dev.s7a.gradle.minecraft.server") version "1.1.0" apply false
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
}

group = "dev.s7a"
version = "1.0.0-SNAPSHOT"

allprojects {
    apply(plugin = "org.jmailen.kotlinter")
    apply(plugin = "com.github.ben-manes.versions")

    repositories {
        mavenCentral()
    }

    tasks.withType<DependencyUpdatesTask> {
        rejectVersionIf {
            isNonStableVersion(candidate.version) && !isNonStableVersion(currentVersion)
        }
    }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "com.github.johnrengelman.shadow")

    repositories {
        maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        maven(url = "https://oss.sonatype.org/content/groups/public/")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }

    val shadowImplementation: Configuration by configurations.creating
    val shadowApi: Configuration by configurations.creating
    configurations["implementation"].extendsFrom(shadowImplementation)
    configurations["api"].extendsFrom(shadowApi)

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    tasks.withType<ShadowJar> {
        configurations = listOf(shadowImplementation, shadowApi)
        archiveClassifier.set("")
    }

    tasks.named("build") {
        dependsOn(tasks.named("shadowJar"))
    }
}

repositories {
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/public/")
}

dependencies {
    testImplementation(project(":api:v1_17"))
    testImplementation(project(":showcase"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("reflect"))
    testImplementation(project(":libs:test"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_16
    targetCompatibility = JavaVersion.VERSION_16
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "16"
    }
}

tasks.withType<Jar> {
    enabled = false
}

/**
 * [version] が非安定かを判定する
 */
fun isNonStableVersion(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

task("updateCodeSnippet") {
    dependsOn(":showcase:updateCodeSnippet")
}

task("dokka") {
    dependsOn(":api:dokkaHtmlMultiModule")
}
