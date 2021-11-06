package dev.s7a.spigot.example.player

import dev.s7a.spigot.listener.registerListener
import dev.s7a.spigot.util.VirtualPlayer
import dev.s7a.spigot.util.VirtualPlayer.Companion.toVirtual
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

/**
 * メインクラス
 */
@Suppress("unused")
class Main : JavaPlugin() {
    private val players = mutableSetOf<Player>()
    private val offlinePlayers = mutableSetOf<OfflinePlayer>()
    private val virtualPlayers = mutableSetOf<VirtualPlayer>()

    override fun onEnable() {
        registerListener(
            object : Listener {
                @EventHandler
                fun on(event: PlayerJoinEvent) {
                    val player = event.player
                    logger.info("players#add: ${players.add(player)}")
                    logger.info("offlinePlayers#add: ${offlinePlayers.add(player)}")
                    logger.info("virtualPlayers#add: ${virtualPlayers.add(player.toVirtual())}")
                }
            }
        )
    }
}
