import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

subprojects {
    dependencies {
        when (project.name) {
            "common" -> {
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

configure<BukkitPluginDescription> {
    name = "ktSpigot"
    version = rootProject.version.toString()
    main = "dev.s7a.spigot.KtSpigot"
    description = "Kotlin makes the Spigot library Easier"
    author = "sya_ri"
    website = "https://github.com/sya-ri/ktSpigot"
    apiVersion = "1.13"
}
