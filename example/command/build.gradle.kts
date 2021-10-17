import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

configure<BukkitPluginDescription> {
    commands {
        register("example") {
            description = "ktSpigot example command"
        }
    }
}
