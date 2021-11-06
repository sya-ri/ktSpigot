package dev.s7a.spigot.example.message

import dev.s7a.spigot.listener.registerListener
import dev.s7a.spigot.util.sendActionBarMessage
import dev.s7a.spigot.util.sendChatMessage
import dev.s7a.spigot.util.sendTitleMessage
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

/**
 * メインクラス
 */
@Suppress("unused")
class Main : JavaPlugin() {
    override fun onEnable() {
        registerListener(
            object : Listener {
                @EventHandler
                fun on(event: PlayerJoinEvent) {
                    val player = event.player
                    player.sendChatMessage("&6&lWelcome to Server!!")
                    player.sendActionBarMessage("#a#lWelcome to Server!!", '#')
                    player.sendTitleMessage("&b&lWelcome to Server!!", "&7Hello")
                }
            }
        )
    }
}
