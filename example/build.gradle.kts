import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

subprojects {
    apply(plugin = "net.minecrell.plugin-yml.bukkit")

    dependencies {
        implementation("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
        implementation(project(":api:v1_17"))
    }

    configure<BukkitPluginDescription> {
        name = "ktSpigot-${project.name}"
        version = rootProject.version.toString()
        main = "dev.s7a.spigot.example.${project.name}.Main"
        depend = listOf("ktSpigot")
        apiVersion = "1.17"
    }
}

tasks.withType<Jar> {
    enabled = false
}
