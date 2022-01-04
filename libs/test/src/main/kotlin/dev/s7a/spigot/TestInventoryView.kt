package dev.s7a.spigot

import org.bukkit.entity.HumanEntity
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryView

class TestInventoryView(private val player: HumanEntity) : InventoryView() {
    private var topInventory: Inventory = TestInventory.Crafting

    fun setTopInventory(inventory: Inventory) {
        topInventory = inventory
    }

    override fun getTopInventory(): Inventory {
        return topInventory
    }

    override fun getBottomInventory(): Inventory {
        TODO("Not yet implemented")
    }

    override fun getPlayer(): HumanEntity {
        return player
    }

    override fun getType(): InventoryType {
        TODO("Not yet implemented")
    }

    override fun getTitle(): String {
        TODO("Not yet implemented")
    }
}
