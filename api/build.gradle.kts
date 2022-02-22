import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import org.jetbrains.dokka.gradle.DokkaTask
import java.net.URL

plugins {
    `maven-publish`
    signing
    id("org.jetbrains.dokka") version "1.6.10"
}

subprojects {
    apply(plugin = "net.minecrell.plugin-yml.bukkit")
    apply(plugin = "org.gradle.maven-publish")
    apply(plugin = "signing")

    version = rootProject.version

    dependencies {
        when (project.name) {
            "v1_8" -> {
                compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
                shadowApi(project(":api:common"))
            }
            "v1_9" -> {
                compileOnly("org.spigotmc:spigot-api:1.9.4-R0.1-SNAPSHOT")
                shadowApi(project(":api:v1_8"))
            }
            "v1_10" -> {
                compileOnly("org.spigotmc:spigot-api:1.10.2-R0.1-SNAPSHOT")
                shadowApi(project(":api:v1_9"))
            }
            "v1_11" -> {
                compileOnly("org.spigotmc:spigot-api:1.11.2-R0.1-SNAPSHOT")
                shadowApi(project(":api:v1_10"))
            }
            "v1_12" -> {
                compileOnly("org.spigotmc:spigot-api:1.12.2-R0.1-SNAPSHOT")
                shadowApi(project(":api:v1_11"))
            }
            "v1_13" -> {
                compileOnly("org.spigotmc:spigot-api:1.13.2-R0.1-SNAPSHOT")
                shadowApi(project(":api:v1_12"))
            }
            "v1_14" -> {
                compileOnly("org.spigotmc:spigot-api:1.14.4-R0.1-SNAPSHOT")
                shadowApi(project(":api:v1_13"))
            }
            "v1_15" -> {
                compileOnly("org.spigotmc:spigot-api:1.15.2-R0.1-SNAPSHOT")
                shadowApi(project(":api:v1_14"))
            }
            "v1_16" -> {
                compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
                shadowApi(project(":api:v1_15"))
            }
            "v1_17" -> {
                compileOnly("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
                shadowApi(project(":api:v1_16"))
            }
        }
    }

    tasks.withType<ShadowJar> {
        archiveBaseName.set("ktSpigot-${project.name}")
    }

    configure<BukkitPluginDescription> {
        name = "ktSpigot"
        version = "${rootProject.version}(${project.name})"
        main = "dev.s7a.spigot.KtSpigot"
        description = "A Library that Simplifies Spigot with Kotlin."
        author = "sya_ri"
        website = "https://github.com/sya-ri/ktSpigot"
        apiVersion = when (project.name) {
            "v1_13" -> "1.13"
            "v1_14" -> "1.14"
            "v1_15" -> "1.15"
            "v1_16" -> "1.16"
            "v1_17" -> "1.17"
            else -> null
        }
    }

    val sourceJar by tasks.registering(Jar::class) {
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }

    publishing {
        repositories {
            maven {
                url = uri(
                    if (version.toString().endsWith("SNAPSHOT")) {
                        "https://s01.oss.sonatype.org/content/repositories/snapshots/"
                    } else {
                        "https://s01.oss.sonatype.org/content/groups/staging/"
                    }
                )
                credentials {
                    username = project.properties["credentials.username"].toString()
                    password = project.properties["credentials.password"].toString()
                }
            }
        }
        publications {
            register<MavenPublication>("maven") {
                groupId = "dev.s7a"
                artifactId = "ktSpigot-${project.name}"
                from(components["kotlin"])
                artifact(sourceJar.get())
                pom {
                    packaging = "pom"
                    name.set("ktSpigot-${project.name}")
                    description.set("A Library that Simplifies Spigot with Kotlin.")
                    url.set("https://github.com/sya-ri/ktSpigot")
                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://github.com/sya-ri/ktSpigot/blob/master/LICENSE")
                        }
                    }
                    developers {
                        developer {
                            id.set("sya-ri")
                            name.set("sya-ri")
                            email.set("sya79lua@gmail.com")
                        }
                    }
                    scm {
                        url.set("https://github.com/sya-ri/ktSpigot.git")
                    }
                }
            }
        }
    }

    signing {
        sign(publishing.publications["maven"])
    }
}

tasks.withType<Jar> {
    enabled = false
}

val dokkaHtml by tasks.getting(DokkaTask::class) {
    moduleName.set("ktSpigot")
    val dokkaDir = rootProject.projectDir.resolve("dokka")
    val version = rootProject.version.toString()
    outputDirectory.set(file(dokkaDir.resolve(version)))
    dependencies {
        dokkaPlugin("org.jetbrains.dokka:versioning-plugin:1.6.10")
        compileOnly("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
    }
    pluginsMapConfiguration.set(
        mapOf(
            "org.jetbrains.dokka.versioning.VersioningPlugin" to """
                {
                    "version": "$version",
                    "olderVersionsDir": "$dokkaDir"
                }
            """.trimIndent()
        )
    )
    dokkaSourceSets {
        named("main") {
            subprojects.forEach {
                if (it.projectDir.resolve("src").exists()) {
                    sourceLink {
                        localDirectory.set(file("${it.name}/src/main/kotlin"))
                        remoteUrl.set(URL("https://github.com/sya-ri/ktSpigot/blob/master/api/${it.name}/src/main/kotlin"))
                        remoteLineSuffix.set("#L")
                    }
                    sourceRoots.from(it.sourceSets.main.get().allSource)
                    externalDocumentationLink {
                        url.set(URL("https://hub.spigotmc.org/javadocs/spigot/"))
                        packageListUrl.set(URL("https://hub.spigotmc.org/javadocs/spigot/element-list"))
                    }
                    externalDocumentationLink {
                        url.set(URL("https://ci.md-5.net/job/BungeeCord/ws/chat/target/apidocs/"))
                        packageListUrl.set(URL("https://ci.md-5.net/job/BungeeCord/ws/chat/target/apidocs/element-list"))
                    }
                }
            }
        }
    }
}
