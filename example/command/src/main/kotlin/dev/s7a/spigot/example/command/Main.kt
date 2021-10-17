package dev.s7a.spigot.example.command

import org.bukkit.plugin.java.JavaPlugin

/**
 * メインクラス
 */
@Suppress("unused")
class Main : JavaPlugin() {
    override fun onEnable() {
        exampleCommand()
    }
}
