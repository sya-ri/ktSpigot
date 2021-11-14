import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.dokka.gradle.DokkaMultiModuleTask
import org.jetbrains.dokka.versioning.VersioningConfiguration
import org.jetbrains.dokka.versioning.VersioningPlugin

buildscript {
    dependencies {
        classpath("org.jetbrains.dokka:versioning-plugin:1.5.31")
    }
}

plugins {
    kotlin("jvm") version "1.5.31"
    id("org.jmailen.kotlinter") version "3.7.0"
    id("com.github.ben-manes.versions") version "0.39.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.0" apply false
    id("com.github.johnrengelman.shadow") version "7.1.0" apply false
    id("org.jetbrains.dokka") version "1.5.31"
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
            freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
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
    testImplementation("com.github.seeseemelk:MockBukkit-v1.17:1.10.3")
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

val dokkaHtmlMultiModule by tasks.getting(DokkaMultiModuleTask::class) {
    val dokkaDir = projectDir.resolve("dokka")
    val version = rootProject.version.toString()
    outputDirectory.set(file(dokkaDir.resolve(version)))
    dependencies {
        dokkaPlugin("org.jetbrains.dokka:versioning-plugin:1.5.31")
    }
    pluginConfiguration<VersioningPlugin, VersioningConfiguration> {
        this.version = version
        olderVersionsDir = dokkaDir
    }
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
