package dev.s7a.ktspigot

import org.bukkit.plugin.PluginDescriptionFile
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.java.JavaPluginLoader
import java.io.File

class TestPlugin(
    loader: JavaPluginLoader,
    description: PluginDescriptionFile,
    dataFolder: File,
    file: File
) : JavaPlugin(loader, description, dataFolder, file) {
    fun addCommand(name: String) {
        KtSpigotTest.setCommand(name, this)
    }
}
