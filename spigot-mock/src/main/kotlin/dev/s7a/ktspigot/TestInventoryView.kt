package dev.s7a.ktspigot

import org.bukkit.entity.HumanEntity
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryView

class TestInventoryView(
    private val player: HumanEntity,
    private var topInventory: Inventory,
    private val bottomInventory: Inventory,
    private val title: String
) : InventoryView() {
    fun setTopInventory(topInventory: Inventory) {
        this.topInventory = topInventory
    }

    override fun getTopInventory(): Inventory {
        return topInventory
    }

    override fun getBottomInventory(): Inventory {
        return bottomInventory
    }

    override fun getPlayer(): HumanEntity {
        return player
    }

    override fun getType(): InventoryType {
        return topInventory.type
    }

    override fun getTitle(): String {
        return title
    }
}
