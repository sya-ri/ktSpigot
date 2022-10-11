package dev.s7a.ktspigot

import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Villager
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.MainHand
import org.bukkit.inventory.Merchant
import org.bukkit.inventory.PlayerInventory
import java.util.UUID
import kotlin.test.assertEquals

abstract class TestHumanEntity(
    uniqueId: UUID,
    name: String,
    type: EntityType,
    location: Location
) : TestLivingEntity(uniqueId, name, type, location), HumanEntity {
    private var inventoryView = TestInventoryView(this, TestInventory.Crafting(), TestInventory.Player(this), "Inventory")

    fun assertInventoryView(chest: InventoryType) {
        assertEquals(chest, inventoryView.topInventory.type)
    }

    override fun getInventory(): PlayerInventory {
        TODO("Not yet implemented")
    }

    override fun getEnderChest(): Inventory {
        TODO("Not yet implemented")
    }

    override fun getMainHand(): MainHand {
        TODO("Not yet implemented")
    }

    override fun setWindowProperty(prop: InventoryView.Property, value: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun getOpenInventory(): InventoryView {
        return inventoryView
    }

    override fun openInventory(inventory: Inventory): InventoryView? {
        return inventoryView.apply {
            topInventory = inventory
        }
    }

    override fun openInventory(inventory: InventoryView) {
        TODO("Not yet implemented")
    }

    override fun openWorkbench(location: Location?, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun openEnchanting(location: Location?, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun openMerchant(trader: Villager, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun openMerchant(merchant: Merchant, force: Boolean): InventoryView? {
        TODO("Not yet implemented")
    }

    override fun closeInventory() {
        val lastInventoryView = openInventory
        Bukkit.getPluginManager().callEvent(InventoryCloseEvent(lastInventoryView))
        inventoryView.topInventory = TestInventory.Crafting()
    }

    override fun getItemInHand(): ItemStack {
        TODO("Not yet implemented")
    }

    override fun setItemInHand(item: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun getItemOnCursor(): ItemStack {
        TODO("Not yet implemented")
    }

    override fun setItemOnCursor(item: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun hasCooldown(material: Material): Boolean {
        TODO("Not yet implemented")
    }

    override fun getCooldown(material: Material): Int {
        TODO("Not yet implemented")
    }

    override fun setCooldown(material: Material, ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getSleepTicks(): Int {
        TODO("Not yet implemented")
    }

    override fun sleep(location: Location, force: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun wakeup(setSpawnLocation: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getBedLocation(): Location {
        TODO("Not yet implemented")
    }

    override fun getGameMode(): GameMode {
        TODO("Not yet implemented")
    }

    override fun setGameMode(mode: GameMode) {
        TODO("Not yet implemented")
    }

    override fun isBlocking(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isHandRaised(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getItemInUse(): ItemStack? {
        TODO("Not yet implemented")
    }

    override fun getExpToLevel(): Int {
        TODO("Not yet implemented")
    }

    override fun getAttackCooldown(): Float {
        TODO("Not yet implemented")
    }

    override fun discoverRecipe(recipe: NamespacedKey): Boolean {
        TODO("Not yet implemented")
    }

    override fun discoverRecipes(recipes: MutableCollection<NamespacedKey>): Int {
        TODO("Not yet implemented")
    }

    override fun undiscoverRecipe(recipe: NamespacedKey): Boolean {
        TODO("Not yet implemented")
    }

    override fun undiscoverRecipes(recipes: MutableCollection<NamespacedKey>): Int {
        TODO("Not yet implemented")
    }

    override fun hasDiscoveredRecipe(recipe: NamespacedKey): Boolean {
        TODO("Not yet implemented")
    }

    override fun getDiscoveredRecipes(): MutableSet<NamespacedKey> {
        TODO("Not yet implemented")
    }

    override fun getShoulderEntityLeft(): Entity? {
        TODO("Not yet implemented")
    }

    override fun setShoulderEntityLeft(entity: Entity?) {
        TODO("Not yet implemented")
    }

    override fun getShoulderEntityRight(): Entity? {
        TODO("Not yet implemented")
    }

    override fun setShoulderEntityRight(entity: Entity?) {
        TODO("Not yet implemented")
    }

    override fun dropItem(dropAll: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun getExhaustion(): Float {
        TODO("Not yet implemented")
    }

    override fun setExhaustion(value: Float) {
        TODO("Not yet implemented")
    }

    override fun getSaturation(): Float {
        TODO("Not yet implemented")
    }

    override fun setSaturation(value: Float) {
        TODO("Not yet implemented")
    }

    override fun getFoodLevel(): Int {
        TODO("Not yet implemented")
    }

    override fun setFoodLevel(value: Int) {
        TODO("Not yet implemented")
    }

    override fun getSaturatedRegenRate(): Int {
        TODO("Not yet implemented")
    }

    override fun setSaturatedRegenRate(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getUnsaturatedRegenRate(): Int {
        TODO("Not yet implemented")
    }

    override fun setUnsaturatedRegenRate(ticks: Int) {
        TODO("Not yet implemented")
    }

    override fun getStarvationRate(): Int {
        TODO("Not yet implemented")
    }

    override fun setStarvationRate(ticks: Int) {
        TODO("Not yet implemented")
    }
}
