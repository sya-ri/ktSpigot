import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

subprojects {
    apply(plugin = "net.minecrell.plugin-yml.bukkit")

    dependencies {
        when (project.name) {
            "common" -> {
                compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
            }
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
        description = "Kotlin makes the Spigot library Easier"
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
}

tasks.withType<Jar> {
    enabled = false
}
