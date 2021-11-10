import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

configure<BukkitPluginDescription> {
    commands {
        register("inventory") {
            description = "ktSpigot example inventory"
        }
    }
}
