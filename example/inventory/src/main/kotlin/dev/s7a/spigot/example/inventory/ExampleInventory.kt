package dev.s7a.spigot.example.inventory

import dev.s7a.spigot.example.inventory.Main.Companion.plugin
import dev.s7a.spigot.inventory.ktInventory
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * インベントリの例
 */
object ExampleInventory {
    fun open(player: Player) {
        plugin.ktInventory("&0&lインベントリ", 1) {
            setItem(4, ItemStack(Material.STONE)) {
                setItem(4, ItemStack(Material.COBBLESTONE))
            }
        }.open(player)
    }
}
