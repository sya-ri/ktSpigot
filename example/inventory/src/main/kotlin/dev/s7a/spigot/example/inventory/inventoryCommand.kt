package dev.s7a.spigot.example.inventory

import dev.s7a.spigot.command.ktCommand
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * インベントリを開くコマンド
 */
fun JavaPlugin.inventoryCommand() {
    ktCommand("inventory") {
        execute { (sender) ->
            if (sender is Player) {
                ExampleInventory.open(sender)
            }
        }
    }
}
