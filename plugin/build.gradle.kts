import net.minecrell.pluginyml.bukkit.BukkitPluginDescription
import net.minecrell.pluginyml.bungee.BungeePluginDescription

subprojects {
    dependencies {
        when (project.name) {
            "bungee" -> {
                compileOnly("net.md-5:bungeecord-api:1.18-R0.1-SNAPSHOT")
            }
            "v1_8" -> {
                compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
            }
            else -> {
                shadowApi(project(":plugin:v1_8"))
            }
        }
        shadowApi(project(":api:${project.name}"))
    }

    if (project.name != "bungee") {
        apply(plugin = "net.minecrell.plugin-yml.bungee")

        configure<BungeePluginDescription> {
            name = "ktSpigot"
            version = "${rootProject.version}(${project.name})"
            main = "dev.s7a.ktspigot.KtSpigot"
            description = "A Library that Simplifies Spigot with Kotlin."
            author = "sya_ri"
        }
    } else {
        apply(plugin = "net.minecrell.plugin-yml.bukkit")

        configure<BukkitPluginDescription> {
            name = "ktSpigot"
            version = "${rootProject.version}(${project.name})"
            main = "dev.s7a.ktspigot.KtSpigot"
            description = "A Library that Simplifies Spigot with Kotlin."
            author = "sya_ri"
            website = "https://github.com/sya-ri/ktSpigot"
            apiVersion = when (project.name) {
                "v1_13" -> "1.13"
                "v1_14" -> "1.14"
                "v1_15" -> "1.15"
                "v1_16" -> "1.16"
                "v1_17" -> "1.17"
                "v1_18" -> "1.18"
                else -> null
            }
        }
    }
}
