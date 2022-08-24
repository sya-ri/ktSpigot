package dev.s7a.ktspigot

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.HumanEntity
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import java.util.HashMap

open class TestInventory(
    private val holder: InventoryHolder?,
    private val type: InventoryType,
    private val size: Int = type.defaultSize,
    val title: String = type.defaultTitle
) : Inventory {
    object Crafting : TestInventory(null, InventoryType.CRAFTING)

    private val items = arrayOfNulls<ItemStack>(size)

    override fun iterator(): MutableListIterator<ItemStack> {
        TODO("Not yet implemented")
    }

    override fun iterator(index: Int): MutableListIterator<ItemStack> {
        TODO("Not yet implemented")
    }

    override fun getSize(): Int {
        return size
    }

    override fun getMaxStackSize(): Int {
        TODO("Not yet implemented")
    }

    override fun setMaxStackSize(size: Int) {
        TODO("Not yet implemented")
    }

    override fun getItem(index: Int): ItemStack? {
        return items[index]
    }

    override fun setItem(index: Int, item: ItemStack?) {
        items[index] = item
    }

    override fun addItem(vararg items: ItemStack?): HashMap<Int, ItemStack> {
        TODO("Not yet implemented")
    }

    override fun removeItem(vararg items: ItemStack?): HashMap<Int, ItemStack> {
        TODO("Not yet implemented")
    }

    override fun getContents(): Array<ItemStack?> {
        return items
    }

    override fun setContents(items: Array<out ItemStack>) {
        TODO("Not yet implemented")
    }

    override fun getStorageContents(): Array<ItemStack> {
        TODO("Not yet implemented")
    }

    override fun setStorageContents(items: Array<out ItemStack>) {
        TODO("Not yet implemented")
    }

    override fun contains(material: Material): Boolean {
        TODO("Not yet implemented")
    }

    override fun contains(item: ItemStack?): Boolean {
        TODO("Not yet implemented")
    }

    override fun contains(material: Material, amount: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun contains(item: ItemStack?, amount: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsAtLeast(item: ItemStack?, amount: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun all(material: Material): HashMap<Int, out ItemStack> {
        TODO("Not yet implemented")
    }

    override fun all(item: ItemStack?): HashMap<Int, out ItemStack> {
        TODO("Not yet implemented")
    }

    override fun first(material: Material): Int {
        TODO("Not yet implemented")
    }

    override fun first(item: ItemStack): Int {
        TODO("Not yet implemented")
    }

    override fun firstEmpty(): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun remove(material: Material) {
        TODO("Not yet implemented")
    }

    override fun remove(item: ItemStack) {
        TODO("Not yet implemented")
    }

    override fun clear(index: Int) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun getViewers(): MutableList<HumanEntity> {
        TODO("Not yet implemented")
    }

    override fun getType(): InventoryType {
        return type
    }

    override fun getHolder(): InventoryHolder? {
        return holder
    }

    override fun getLocation(): Location? {
        TODO("Not yet implemented")
    }
}
