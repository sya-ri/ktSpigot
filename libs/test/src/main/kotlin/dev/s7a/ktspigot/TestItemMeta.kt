package dev.s7a.ktspigot

import com.google.common.collect.Multimap
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.tags.CustomItemTagContainer
import org.bukkit.persistence.PersistentDataContainer

open class TestItemMeta(meta: ItemMeta?) : ItemMeta {
    private var displayName: String? = null
    private var localizedName: String? = null
    private var lore: List<String>? = null
    private var customModelData: Int? = null

    init {
        if (meta != null) {
            displayName = meta.displayName
            localizedName = meta.localizedName
            lore = meta.lore
            customModelData = meta.customModelData
        }
    }

    override fun clone(): ItemMeta {
        return TestItemMeta(null).also {
            it.displayName = displayName
            it.localizedName = localizedName
            it.lore = lore
            it.customModelData = customModelData
        }
    }

    override fun serialize(): MutableMap<String, Any> {
        TODO("Not yet implemented")
    }

    override fun getPersistentDataContainer(): PersistentDataContainer {
        TODO("Not yet implemented")
    }

    override fun hasDisplayName(): Boolean {
        return displayName.isNullOrEmpty().not()
    }

    override fun getDisplayName(): String {
        return displayName.orEmpty()
    }

    override fun setDisplayName(name: String?) {
        displayName = name
    }

    override fun hasLocalizedName(): Boolean {
        return localizedName.isNullOrEmpty().not()
    }

    override fun getLocalizedName(): String {
        return localizedName.orEmpty()
    }

    override fun setLocalizedName(name: String?) {
        localizedName = name
    }

    override fun hasLore(): Boolean {
        return lore.isNullOrEmpty().not()
    }

    override fun getLore(): List<String>? {
        return lore.orEmpty()
    }

    override fun setLore(lore: List<String>?) {
        this.lore = lore
    }

    override fun hasCustomModelData(): Boolean {
        return customModelData != null && customModelData != 0
    }

    override fun getCustomModelData(): Int {
        return customModelData ?: 0
    }

    override fun setCustomModelData(data: Int?) {
        customModelData = data
    }

    override fun hasEnchants(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasEnchant(ench: Enchantment): Boolean {
        TODO("Not yet implemented")
    }

    override fun getEnchantLevel(ench: Enchantment): Int {
        TODO("Not yet implemented")
    }

    override fun getEnchants(): MutableMap<Enchantment, Int> {
        TODO("Not yet implemented")
    }

    override fun addEnchant(ench: Enchantment, level: Int, ignoreLevelRestriction: Boolean): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeEnchant(ench: Enchantment): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasConflictingEnchant(ench: Enchantment): Boolean {
        TODO("Not yet implemented")
    }

    override fun addItemFlags(vararg itemFlags: ItemFlag?) {
        TODO("Not yet implemented")
    }

    override fun removeItemFlags(vararg itemFlags: ItemFlag?) {
        TODO("Not yet implemented")
    }

    override fun getItemFlags(): MutableSet<ItemFlag> {
        TODO("Not yet implemented")
    }

    override fun hasItemFlag(flag: ItemFlag): Boolean {
        TODO("Not yet implemented")
    }

    override fun isUnbreakable(): Boolean {
        TODO("Not yet implemented")
    }

    override fun setUnbreakable(unbreakable: Boolean) {
        TODO("Not yet implemented")
    }

    override fun hasAttributeModifiers(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAttributeModifiers(): Multimap<Attribute, AttributeModifier>? {
        TODO("Not yet implemented")
    }

    override fun getAttributeModifiers(slot: EquipmentSlot): Multimap<Attribute, AttributeModifier> {
        TODO("Not yet implemented")
    }

    override fun getAttributeModifiers(attribute: Attribute): MutableCollection<AttributeModifier>? {
        TODO("Not yet implemented")
    }

    override fun addAttributeModifier(attribute: Attribute, modifier: AttributeModifier): Boolean {
        TODO("Not yet implemented")
    }

    override fun setAttributeModifiers(attributeModifiers: Multimap<Attribute, AttributeModifier>?) {
        TODO("Not yet implemented")
    }

    override fun removeAttributeModifier(attribute: Attribute): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeAttributeModifier(slot: EquipmentSlot): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeAttributeModifier(attribute: Attribute, modifier: AttributeModifier): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAsString(): String {
        TODO("Not yet implemented")
    }

    override fun getCustomTagContainer(): CustomItemTagContainer {
        TODO("Not yet implemented")
    }

    override fun setVersion(version: Int) {
        TODO("Not yet implemented")
    }
}
