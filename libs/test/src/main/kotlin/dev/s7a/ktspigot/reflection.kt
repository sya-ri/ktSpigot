package dev.s7a.ktspigot

import org.bukkit.command.PluginCommand
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin

fun JavaPlugin.setEnabled(enabled: Boolean) {
    val setEnabled = JavaPlugin::class.java.getDeclaredMethod("setEnabled", Boolean::class.java)
    setEnabled.isAccessible = true
    setEnabled.invoke(this, enabled)
}

fun PluginCommand(name: String, owner: JavaPlugin): PluginCommand {
    val constructor = PluginCommand::class.java.getDeclaredConstructor(String::class.java, Plugin::class.java)
    constructor.isAccessible = true
    return constructor.newInstance(name, owner)
}
