package dev.s7a.spigot.example.listener

import dev.s7a.spigot.example.listener.Main.Companion.plugin
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.server.ServerCommandEvent

object EventListener : Listener {
    @EventHandler
    fun on(event: ServerCommandEvent) {
        plugin.logger.info("サーバーからコマンドを実行: ${event.command}")
    }

    @EventHandler
    fun on(event: PlayerJoinEvent) {
        plugin.logger.info("プレイヤーがサーバーに参加: ${event.player.name}")
    }
}
