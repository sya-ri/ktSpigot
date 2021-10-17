import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

subprojects {
    dependencies {
        when (project.name) {
            "common", "v1_8" -> {
                implementation("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
            }
        }
    }
}

apply(plugin = "net.minecrell.plugin-yml.bukkit")

dependencies {
    subprojects.forEach {
        shadowApi(project(it.path)) {
            exclude("org.spigotmc", "spigot-api")
        }
    }
}

tasks.withType<ShadowJar> {
    archiveBaseName.set("ktSpigot")
}

configure<BukkitPluginDescription> {
    name = "ktSpigot"
    version = rootProject.version.toString()
    main = "dev.s7a.spigot.KtSpigot"
    description = "Kotlin makes the Spigot library Easier"
    author = "sya_ri"
    website = "https://github.com/sya-ri/ktSpigot"
    apiVersion = "1.13"
}
