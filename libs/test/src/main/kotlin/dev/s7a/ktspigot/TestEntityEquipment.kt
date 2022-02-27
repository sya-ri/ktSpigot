package dev.s7a.ktspigot

import org.bukkit.entity.Entity
import org.bukkit.inventory.EntityEquipment
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack

class TestEntityEquipment : EntityEquipment {
    private var helmet: ItemStack? = null

    override fun setItem(slot: EquipmentSlot, item: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun setItem(slot: EquipmentSlot, item: ItemStack?, silent: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getItem(slot: EquipmentSlot): ItemStack {
        TODO("Not yet implemented")
    }

    override fun getItemInMainHand(): ItemStack {
        TODO("Not yet implemented")
    }

    override fun setItemInMainHand(item: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun setItemInMainHand(item: ItemStack?, silent: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getItemInOffHand(): ItemStack {
        TODO("Not yet implemented")
    }

    override fun setItemInOffHand(item: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun setItemInOffHand(item: ItemStack?, silent: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getItemInHand(): ItemStack {
        TODO("Not yet implemented")
    }

    override fun setItemInHand(stack: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun getHelmet(): ItemStack? {
        return helmet
    }

    override fun setHelmet(helmet: ItemStack?) {
        setHelmet(helmet, true)
    }

    override fun setHelmet(helmet: ItemStack?, silent: Boolean) {
        this.helmet = helmet
    }

    override fun getChestplate(): ItemStack? {
        TODO("Not yet implemented")
    }

    override fun setChestplate(chestplate: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun setChestplate(chestplate: ItemStack?, silent: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getLeggings(): ItemStack? {
        TODO("Not yet implemented")
    }

    override fun setLeggings(leggings: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun setLeggings(leggings: ItemStack?, silent: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getBoots(): ItemStack? {
        TODO("Not yet implemented")
    }

    override fun setBoots(boots: ItemStack?) {
        TODO("Not yet implemented")
    }

    override fun setBoots(boots: ItemStack?, silent: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getArmorContents(): Array<ItemStack> {
        TODO("Not yet implemented")
    }

    override fun setArmorContents(items: Array<out ItemStack>) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun getItemInHandDropChance(): Float {
        TODO("Not yet implemented")
    }

    override fun setItemInHandDropChance(chance: Float) {
        TODO("Not yet implemented")
    }

    override fun getItemInMainHandDropChance(): Float {
        TODO("Not yet implemented")
    }

    override fun setItemInMainHandDropChance(chance: Float) {
        TODO("Not yet implemented")
    }

    override fun getItemInOffHandDropChance(): Float {
        TODO("Not yet implemented")
    }

    override fun setItemInOffHandDropChance(chance: Float) {
        TODO("Not yet implemented")
    }

    override fun getHelmetDropChance(): Float {
        TODO("Not yet implemented")
    }

    override fun setHelmetDropChance(chance: Float) {
        TODO("Not yet implemented")
    }

    override fun getChestplateDropChance(): Float {
        TODO("Not yet implemented")
    }

    override fun setChestplateDropChance(chance: Float) {
        TODO("Not yet implemented")
    }

    override fun getLeggingsDropChance(): Float {
        TODO("Not yet implemented")
    }

    override fun setLeggingsDropChance(chance: Float) {
        TODO("Not yet implemented")
    }

    override fun getBootsDropChance(): Float {
        TODO("Not yet implemented")
    }

    override fun setBootsDropChance(chance: Float) {
        TODO("Not yet implemented")
    }

    override fun getHolder(): Entity? {
        TODO("Not yet implemented")
    }
}
