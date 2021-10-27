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
            "v1_13" -> {
                compileOnly("org.spigotmc:spigot-api:1.13.2-R0.1-SNAPSHOT")
                shadowApi(project(":api:v1_8"))
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
            else -> null
        }
    }
}

dependencies {
    subprojects.forEach {
        shadowApi(project(it.path))
    }
}

tasks.withType<Jar> {
    enabled = false
}
