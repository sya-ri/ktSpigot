package dev.s7a.spigot.example.config

import org.bukkit.plugin.java.JavaPlugin

/**
 * メインクラス
 */
@Suppress("unused")
class Main : JavaPlugin() {
    companion object {
        internal lateinit var plugin: JavaPlugin
    }

    init {
        plugin = this
    }

    override fun onEnable() {
        SimpleConfig.load()
        SimpleConfigDirectory.loadAll()
        SimpleConfigRecursiveDirectory.loadAll()
        AdvancedConfig.load()
    }
}
