package dev.s7a.spigot.example.listener

import dev.s7a.spigot.listener.registerListener
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
        registerListener(EventListener)
    }
}
